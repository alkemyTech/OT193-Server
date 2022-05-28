package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.TestimonialDTO;
import com.alkemy.somosmas.models.Testimonial;
import com.alkemy.somosmas.repositories.TestimonialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private ObjectMapper mapper;

    public TestimonialDTO save(TestimonialDTO dto){
        Testimonial testimonial = mapper.convertValue(dto,Testimonial.class);
        testimonialRepository.save(testimonial);
        TestimonialDTO testimonialDTO = mapper.convertValue(testimonial,TestimonialDTO.class);
        System.out.println("testimonio guardado");

        return testimonialDTO;
    }

}