package com.alkemy.somosmas.dtos;

import com.alkemy.somosmas.models.Organization;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideDTO {
    private long id;

    private String imageUrl;

    private Integer order;

    private String text;

    private Organization organization;
 
    public SlideDTO(long id, String imageUrl, Integer order, String text, Organization organization) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.order = order;
        this.text = text;
        this.organization = organization;
    }

    public SlideDTO() {
    }

   
  
}
