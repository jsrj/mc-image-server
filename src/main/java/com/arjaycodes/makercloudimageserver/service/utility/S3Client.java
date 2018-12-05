package com.arjaycodes.makercloudimageserver.service.utility;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class S3Client {

    private Logger log = LoggerFactory.getLogger(S3Client.class);

    private AmazonS3 client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    private void setClient(AmazonS3 client) {
        this.client = client;
    }

    @PostConstruct
    private void initializeAmazon() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        AmazonS3 amazonS3 = AmazonS3Client.builder()
                            .withCredentials(new AWSStaticCredentialsProvider(credentials))
                            .withRegion(Regions.US_EAST_1)
                            .build();

        this.setClient(amazonS3);
    }

    private File convertMultipartToPart(MultipartFile multipartFile) {

        // Extract file type suffix
        String[] fileProps = Objects.requireNonNull(multipartFile.getContentType()).split("/");
        String  fileSuffix = (Objects.nonNull(fileProps[fileProps.length-1])) ? '.'+fileProps[fileProps.length-1] : "";

        // Generate a UUID for the image name
        String generatedName = UUID.randomUUID()
                               .toString()
                               .replaceAll("-+", "")
                               .toUpperCase()
                               +fileSuffix;

        File convertedFile = new File(generatedName);

        try (FileOutputStream fout = new FileOutputStream(convertedFile)) {
            fout.write(multipartFile.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return convertedFile;
    }

    private void pushToS3Bucket(File image) {
        log.info("");
        client.putObject(
                new PutObjectRequest(this.bucketName, image.getName(), image)
                    .withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }

    public String uploadFile(MultipartFile multipartFile) {

        String imageURL = "";
        File convertedImage = this.convertMultipartToPart(multipartFile);

        try {
            imageURL = this.endpointUrl + "/" + this.bucketName + "/" + convertedImage.getName();
            pushToS3Bucket(convertedImage);
        }

        catch (Exception e) {
            log.error(e.getMessage());
        }

        finally {
            boolean tempImageDeleted = convertedImage.delete();
            if (tempImageDeleted) {
                log.info("Staged image deleted successfully.");
            } else {
                log.warn("Could not delete image from staging.");
            }
        }

        return imageURL;
    }
}
