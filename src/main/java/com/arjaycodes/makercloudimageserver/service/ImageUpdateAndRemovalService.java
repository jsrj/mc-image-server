package com.arjaycodes.makercloudimageserver.service;

import com.arjaycodes.makercloudimageserver.projection.StandardResponseDTO;

public interface ImageUpdateAndRemovalService {

    // Updates actual Base-64 Image Encoding by a given ID
    StandardResponseDTO updateImageData (Long imageId);

    // Removes/Deletes an Image Entity from Datastore by a given ID
    StandardResponseDTO deleteImageEntity (Long imageId);
}
