package com.alkemy.somosmas.services;

import java.io.IOException;
import java.util.List;

import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.dtos.SlideRequestDTO;
import com.alkemy.somosmas.dtos.SlidesGetDTO;

public interface SlideService {
    public SlideGetDTO create(SlideRequestDTO slideDTO) throws IOException, Exception;
    public String delete(Long id) throws Exception;
	public SlideGetDTO getSlide(Long id) throws Exception;
	public SlideGetDTO updateSlide(Long id, SlideRequestDTO slideRequestDTO) throws Exception;
    public List<SlidesGetDTO> getAll() throws Exception;
}
