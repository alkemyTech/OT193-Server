package com.alkemy.somosmas.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideRequestDTO {
    private String imageUrl;
    private Integer order;
    private String text;
    private long organizationId;
    private String encodeImg;

   public SlideRequestDTO() {
   }
}
