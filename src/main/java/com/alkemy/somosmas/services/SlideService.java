package com.alkemy.somosmas.services;

import java.util.List;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.models.Slide;

public interface SlideService {

    public Slide create(SlideDTO slideDTO);
    public SlideDTO delete(SlideDTO slideDTO);
    public List<SlideDTO> getSlides();
    public SlideDTO getSlide(Long id);
}
