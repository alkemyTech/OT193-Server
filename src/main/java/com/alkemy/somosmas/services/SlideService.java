package com.alkemy.somosmas.services;

import java.io.IOException;
import java.util.List;

import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.dtos.SlideRequestDTO;
import com.alkemy.somosmas.dtos.SlidesGetDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;


public interface SlideService {
    public SlideGetDTO create(SlideRequestDTO slideDTO) throws  NotAcceptableArgumentException, IOException;
    public String delete(Long id) throws ModelNotFoundException;
	public SlideGetDTO getSlide(Long id) throws ModelNotFoundException;
	public SlideGetDTO updateSlide(Long id, SlideRequestDTO slideRequestDTO) throws ModelNotFoundException, NotAcceptableArgumentException, IOException ;
    public List<SlidesGetDTO> getAllSlides();
}
