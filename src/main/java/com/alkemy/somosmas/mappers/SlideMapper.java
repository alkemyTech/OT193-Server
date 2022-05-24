package com.alkemy.somosmas.mappers;

import java.io.IOException;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.models.Slide;
import com.amazonaws.SdkClientException;

import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    public Slide slideDtoToEntity(SlideDTO slideDto) throws  IOException  {
        Slide slide = new Slide();
        slide.setText(slideDto.getText());
        slide.setImageUrl(slideDto.getImageUrl());
        slide.setOrder(slideDto.getOrder());
        slide.setOrganization(slideDto.getOrganization());

        return slide;
    }

    public SlideGetDTO slideEntityDto(Slide slide) throws SdkClientException, IOException  {
        SlideGetDTO slideGetDTO = new SlideGetDTO();
        slideGetDTO.setImageUrl(slide.getImageUrl());
        slideGetDTO.setText(slide.getText());
        slideGetDTO.setOrder(slide.getOrder());
        slideGetDTO.setOrganizationId(slide.getOrganization().getId());

        return slideGetDTO;
    }
}
