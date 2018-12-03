package com.arjaycodes.makercloudimageserver.controller;

import com.arjaycodes.makercloudimageserver.model.ImageData;
import com.arjaycodes.makercloudimageserver.model.utility.PageInfo;
import com.arjaycodes.makercloudimageserver.projection.ImageResponseDTO;
import com.arjaycodes.makercloudimageserver.projection.StandardResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


// V1 Features & Functionality
@RestController
@RequestMapping(value = "/img/v1")
public class V1ImageController {

    private Logger log = LoggerFactory.getLogger(V1ImageController.class);

    // -- /GET/    -- All Images - Paginated
    @GetMapping(value = "/")
    public ImageResponseDTO getAllImages(
            @RequestParam(value = "pageNumber"   , required = false) Integer pageNumber,   // Defaults to 1
            @RequestParam(value = "imagesPerPage", required = false) Integer imagesPerPage // Defaults to 5
    ) {
        pageNumber    = (Objects.isNull(pageNumber))    ? 1 : pageNumber;
        imagesPerPage = (Objects.isNull(imagesPerPage)) ? 5 : imagesPerPage;

        log.info("/GET/ All Images on Page {}, with {} Images Per Page", pageNumber, imagesPerPage);

        ImageResponseDTO res = new ImageResponseDTO();

        res.setPageInfo(new PageInfo(pageNumber, 25, imagesPerPage));
        res.getData().add(new ImageData(12312341234L, "test", "NO_DATA"));

        return res;
    }

    // -- /GET/    -- All Images by Owner - paginated
    @GetMapping(value = "/{ownerName}")
    public ImageResponseDTO getAllImagesByOwner(
            @PathVariable(value = "ownerName", required = false) String ownerName
    ) {
        log.info("/GET/ All Images for {}\n", ownerName);
        return new ImageResponseDTO();
    }

    // -- /GET/    -- One Image by Owner and Identifier - non-Paginated
    @GetMapping(value = "/{ownerName}/{imageId}")
    public ImageResponseDTO getOneImageByID(
            @PathVariable(value = "ownerName") String ownerName,
            @PathVariable(value =   "imageId") Long   imageId
    ) {
        log.info("/GET/ Image with ID of {} for {}", imageId, ownerName);
        return new ImageResponseDTO();
    }

    // -- /POST/   -- New Image
    @PostMapping(value = "/{ownerName}")
    public StandardResponseDTO postOneImage(
            @PathVariable(value = "ownerName") String ownerName
    ) {
        log.info("/POST/ New Image for {}", ownerName);
        return new StandardResponseDTO();
    }

    // -- /POST/   -- Multiple New Images
    @PostMapping(value = "/{ownerName}/batch")
    public StandardResponseDTO postManyImages(
            @PathVariable(value = "ownerName") String ownerName
    ) {
        log.info("/POST/ Batch of New Images for {}", ownerName);
        return new StandardResponseDTO();
    }

    // -- /PUT/    -- Replace An Image by Identifier and Owner Name
    @PutMapping(value = "/{ownerName}/{imageId}")
    public StandardResponseDTO updateImageByID(
            @PathVariable(value = "ownerName") String ownerName,
            @PathVariable(value =   "imageId") Long   imageId
    ) {
        log.info("/PUT/ Replacement Image by Identifier {} for {}", imageId, ownerName);
        return new StandardResponseDTO();
    }

    // -- /DELETE/ -- An Image by Identifier and Owner Name
    @DeleteMapping(value = "/{ownerName}/{imageId}")
    public StandardResponseDTO deleteImageById(
            @PathVariable(value = "ownerName") String ownerName,
            @PathVariable(value =   "imageId") Long   imageId
    ) {
        log.info("/DELETE/ Replacement Image by Identifier {} for {}", imageId, ownerName);
        return new StandardResponseDTO();
    }
}
