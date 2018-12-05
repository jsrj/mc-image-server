package com.arjaycodes.makercloudimageserver.projection;

import com.arjaycodes.makercloudimageserver.model.ImageData;
import com.arjaycodes.makercloudimageserver.model.utility.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    @Getter @Setter
    private PageInfo pageInfo;

    @Getter
    private final List<ImageData> data = new ArrayList<>();
}
