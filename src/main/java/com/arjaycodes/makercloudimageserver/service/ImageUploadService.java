package com.arjaycodes.makercloudimageserver.service;

import com.arjaycodes.makercloudimageserver.projection.StandardResponse;

public interface ImageUploadService {
    // Adds a new Image Entity by owner
    StandardResponse addImageByOwner(String ownerName);

    // Adds multiple Image Entities by Owner
    StandardResponse addMultipleImagesByOwner(String ownerName);
}
