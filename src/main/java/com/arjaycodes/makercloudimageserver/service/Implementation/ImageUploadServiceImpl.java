package com.arjaycodes.makercloudimageserver.service.Implementation;

import com.arjaycodes.makercloudimageserver.projection.StandardResponse;
import com.arjaycodes.makercloudimageserver.service.ImageUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    Logger log = LoggerFactory.getLogger(ImageUploadServiceImpl.class);

    @Override
    public StandardResponse addImageByOwner(String ownerName) {
        return null;
    }

    @Override
    public StandardResponse addMultipleImagesByOwner(String ownerName) {
        return null;
    }
}
