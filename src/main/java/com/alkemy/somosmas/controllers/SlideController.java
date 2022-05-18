package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.models.Slide;
import com.alkemy.somosmas.services.SlideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Slide")
public class SlideController {
    @Autowired
    SlideService slideService;

    @PostMapping
    public Slide createSlide(@RequestBody SlideDTO slide){
            return this.slideService.create(slide);
    }

    
}
