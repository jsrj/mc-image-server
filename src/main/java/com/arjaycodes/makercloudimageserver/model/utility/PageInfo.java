package com.arjaycodes.makercloudimageserver.model.utility;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

    @Getter @Setter
    public Integer pageNumber;

    @Getter @Setter
    public Integer pageCount;

    @Getter @Setter
    public Integer imagesPerPage;
}
