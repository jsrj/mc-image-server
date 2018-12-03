package com.arjaycodes.makercloudimageserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/img/v1/")
public class V1ImageController {
    // V1 Features & Functionality

    // -- /GET/    -- All Images - Paginated
    // -- /GET/    -- All Images by Owner - paginated
    // -- /GET/    -- One Image by Identifier - non-Paginated
    // -- /POST/   -- New Image
    // -- /POST/   -- Multiple New Images
    // -- /PUT/    -- Replace An Image by Identifier
    // -- /DELETE/ -- An Image by Identifier
}
