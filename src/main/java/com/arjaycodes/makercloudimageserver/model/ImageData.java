package com.arjaycodes.makercloudimageserver.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Entity;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {

    @Id
    @Getter @Setter
    private Long imageIdentifier;

    @Getter @Setter
    private String ownerName;

    @Getter @Setter
    private String b64ImgCode;
}
