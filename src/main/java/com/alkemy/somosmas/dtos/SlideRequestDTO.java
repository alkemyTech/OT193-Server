package com.alkemy.somosmas.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideRequestDTO {

    private String imageUrl;
    private Integer order;
    private String text;

    @NotNull(message = "Id of organization is mandatory")
    private long organizationId;


    @NotBlank(message = "Encode is mandatory")
    private String encodeImg;

   public SlideRequestDTO() {
   }
}
