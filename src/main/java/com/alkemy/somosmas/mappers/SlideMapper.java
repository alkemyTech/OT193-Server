package com.alkemy.somosmas.mappers;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.models.Slide;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    public Slide create(SlideDTO slideDTO){
        Slide slide= new Slide();
        slide.setImageUrl(slideDTO.getImageUrl());
        slide.setOrder_ong(slideDTO.getOrder_ong());
        slide.setOrganizationId(slideDTO.getOrganizationId());
        slide.setText(slideDTO.getText());

        return slide;
    }
}
