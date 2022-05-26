package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.TestimonialDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
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

    public TestimonialDTO updateTestimonial(TestimonialDTO newTestimonialDTO, Long id) throws ModelNotFoundException{
        Testimonial model = this.testimonialRepository.findById(id).orElse(null);
        if(model==null){
            //Excepcion de tipo check heredar de la clase exception
            throw new ModelNotFoundException(id,"Testimonial");
        }
        //TestimonialDTO testimonialDTO = mapper.convertValue(model,TestimonialDTO.class);
        model.setName(newTestimonialDTO.getName());
        model.setImage(newTestimonialDTO.getImage());
        model.setContent(newTestimonialDTO.getContent());


        this.testimonialRepository.save(model);
        System.out.println("Testimonio actualizado");

        return newTestimonialDTO;
    }

}