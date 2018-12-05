package com.arjaycodes.makercloudimageserver.service;

import com.arjaycodes.makercloudimageserver.projection.StandardResponse;

public interface ImageUpdateAndRemovalService {

    // Updates actual Base-64 Image Encoding by a given ID
    StandardResponse updateImageData (Long imageId);

    // Removes/Deletes an Image Entity from Datastore by a given ID
    StandardResponse deleteImageEntity (Long imageId);
}
