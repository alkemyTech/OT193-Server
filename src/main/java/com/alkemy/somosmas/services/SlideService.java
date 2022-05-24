package com.alkemy.somosmas.services;

import java.io.IOException;

import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.dtos.SlideRequestDTO;

public interface SlideService {

    public SlideGetDTO create(SlideRequestDTO slideDTO) throws IOException, Exception;
 
}
