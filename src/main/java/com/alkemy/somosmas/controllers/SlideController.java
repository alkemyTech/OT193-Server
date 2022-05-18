package com.alkemy.somosmas.controllers;



import java.util.*;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.models.Slide;
import com.alkemy.somosmas.services.SlideService;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Slide")
public class SlideController {
    @Autowired
    SlideService slideService;

    @PostMapping
    public Slide createSlide(@RequestBody SlideDTO slide){
            return this.slideService.create(slide);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getSlide(@PathVariable("id") Long id){ 
          Map<String, Object> response=new HashMap<>();
          try {
            return new ResponseEntity<SlideDTO>(this.slideService.getSlide(id), null, HttpStatus.SC_OK);
          } catch (Exception e) {
              response.put("mensaje", "Ocurrio un error al mostrar el slide con id: ".concat(id.toString()));
              response.put("error", e.getMessage());
              return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.SC_NOT_FOUND);
          }      
    }


    
}
