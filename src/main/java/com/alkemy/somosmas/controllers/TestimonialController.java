package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.TestimonialDTO;
import com.alkemy.somosmas.services.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/testimonial")
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @PostMapping
    public ResponseEntity<TestimonialDTO> save(@Valid @RequestBody TestimonialDTO testimonial){
        TestimonialDTO testimonialGuardado = testimonialService.save(testimonial);
        return ResponseEntity.status(HttpStatus.CREATED).body(testimonialGuardado);
    }

}
