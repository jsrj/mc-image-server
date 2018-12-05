package com.arjaycodes.makercloudimageserver.controller;

import com.arjaycodes.makercloudimageserver.model.ImageData;
import com.arjaycodes.makercloudimageserver.model.utility.PageInfo;
import com.arjaycodes.makercloudimageserver.projection.ImageResponse;
import com.arjaycodes.makercloudimageserver.projection.StandardResponse;
import com.arjaycodes.makercloudimageserver.service.utility.S3Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


// V1 Features & Functionality
@RestController
@RequestMapping(value = "/img/v1")
public class V1ImageController {

    private Logger log = LoggerFactory.getLogger(V1ImageController.class);

    // Amazon Utility
    private S3Client s3Client;

    @Autowired
    V1ImageController(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    // -- /GET/    -- All Images - Paginated
    @GetMapping(value = "/")
    public ImageResponse getAllImages(
            @RequestParam(value = "pageNumber"   , required = false) Integer pageNumber,   // Defaults to 1
            @RequestParam(value = "imagesPerPage", required = false) Integer imagesPerPage // Defaults to 5
    ) {
        pageNumber    = (Objects.isNull(pageNumber))    ? 1 : pageNumber;
        imagesPerPage = (Objects.isNull(imagesPerPage)) ? 5 : imagesPerPage;

        log.info("/GET/ All Images on Page {}, with {} Images Per Page", pageNumber, imagesPerPage);

        ImageResponse res = new ImageResponse();

        res.setPageInfo(new PageInfo(pageNumber, 25, imagesPerPage));
        res.getData().add(new ImageData(12312341234L, "test", "NO_DATA"));

        return res;
    }

    // -- /GET/    -- All Images by Owner - paginated
    @GetMapping(value = "/{ownerName}")
    public ImageResponse getAllImagesByOwner(
            @PathVariable(value = "ownerName", required = false) String ownerName
    ) {
        log.info("/GET/ All Images for {}\n", ownerName);
        return new ImageResponse();
    }

    // -- /GET/    -- One Image by Owner and Identifier - non-Paginated
    @GetMapping(value = "/{ownerName}/{imageId}")
    public ImageResponse getOneImageByID(
            @PathVariable(value = "ownerName") String ownerName,
            @PathVariable(value =   "imageId") Long   imageId
    ) {
        log.info("/GET/ Image with ID of {} for {}", imageId, ownerName);
        return new ImageResponse();
    }

    // -- /POST/   -- New Image
    @PostMapping(value = "/{ownerName}")
    public String postOneImage(
            @PathVariable(value = "ownerName") String    ownerName,
            @RequestPart(value =  "imageFile") MultipartFile image
    ) {
        log.info("/POST/ New Image for {}", ownerName);
        return this.s3Client.uploadFile(image);
    }

    // -- /POST/   -- Multiple New Images
    @PostMapping(value = "/{ownerName}/batch")
    public StandardResponse postManyImages(
            @PathVariable(value = "ownerName") String ownerName
    ) {
        log.info("/POST/ Batch of New Images for {}", ownerName);
        return new StandardResponse();
    }

    // -- /PUT/    -- Replace An Image by Identifier and Owner Name
    @PutMapping(value = "/{ownerName}/{imageId}")
    public StandardResponse updateImageByID(
            @PathVariable(value = "ownerName") String ownerName,
            @PathVariable(value =   "imageId") Long   imageId
    ) {
        log.info("/PUT/ Replacement Image by Identifier {} for {}", imageId, ownerName);
        return new StandardResponse();
    }

    // -- /DELETE/ -- An Image by Identifier and Owner Name
    @DeleteMapping(value = "/{ownerName}/{imageId}")
    public StandardResponse deleteImageById(
            @PathVariable(value = "ownerName") String ownerName,
            @PathVariable(value =   "imageId") Long   imageId
    ) {
        log.info("/DELETE/ Replacement Image by Identifier {} for {}", imageId, ownerName);
        return new StandardResponse();
    }
}
