package com.alkemy.somosmas.mappers;

import java.util.*;
import java.util.stream.Collectors;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.dtos.SlidegetSlidesDTO;
import com.alkemy.somosmas.models.Slide;
import org.springframework.stereotype.Component;



@Component
public class SlideMapper {

   
    public SlidegetSlidesDTO getSlidesByUrlimgAndOrder(Slide slide){
        SlidegetSlidesDTO slideDTO= new SlidegetSlidesDTO();
        slideDTO.setImageUrl(slide.getImageUrl());
        slideDTO.setOrder_ong(slide.getOrder_ong());
        return slideDTO;
    }

    
    public Slide create(SlideDTO slideDTO){
        Slide slide= new Slide();
        slide.setImageUrl(slideDTO.getImageUrl());
        slide.setOrder_ong(slideDTO.getOrder_ong());
        slide.setOrganizationId(slideDTO.getOrganizationId());
        slide.setText(slideDTO.getText());

        return slide;
    }

    public SlideDTO getSlide(Slide slide){
        SlideDTO slideDTO= new SlideDTO();
        slideDTO.setImageUrl(slide.getImageUrl());
        slideDTO.setOrder_ong(slide.getOrder_ong());
        slideDTO.setOrganizationId(slide.getOrganizationId());
        slideDTO.setText(slide.getText());
        return slideDTO;
    }

    

    public List<SlidegetSlidesDTO> getSlides(List<Slide> slides){
        List<SlidegetSlidesDTO> slidesList=slides
        .stream()
        .map(i->this.getSlidesByUrlimgAndOrder(i))
        .collect(Collectors.toList());
        return slidesList;

    }
}
