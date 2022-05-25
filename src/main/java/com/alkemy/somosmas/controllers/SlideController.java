package com.alkemy.somosmas.controllers;

import java.util.HashMap;
import java.util.Map;


import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.dtos.SlideRequestDTO;
import com.alkemy.somosmas.services.SlideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Slide")
public class SlideController {
    @Autowired
    SlideService slideService;

    @PostMapping
	public ResponseEntity<?> create(@RequestBody SlideRequestDTO slideDTO){
		Map<String, Object> response=new HashMap<>();
		SlideGetDTO slide = new SlideGetDTO();
		try {
			slide= this.slideService.create(slideDTO);
			return ResponseEntity.ok().body(slide);
		 } catch (Exception e) {
			 response.put("mensaje", "Ocurrio un error al crear slide ");
			   response.put("error", e.getMessage());
			   return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.NOT_FOUND);
		 }
   			
	}

    
	@GetMapping("/{id}")
    public ResponseEntity<?> getSlide(@PathVariable Long id) {
		Map<String, Object> response=new HashMap<>();
        SlideGetDTO slide = new SlideGetDTO();
        try {
           slide= slideService.getSlide(id);
		   return ResponseEntity.ok().body(slide);
        } catch (Exception e) {
			response.put("mensaje", "Ocurrio un error al mostrar el slide con id: ".concat(id.toString()));
              response.put("error", e.getMessage());
              return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.NOT_FOUND);
        }
       
    }

    @PutMapping("/{id}")  
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SlideRequestDTO slideRequestDTO)   
	{  
		Map<String, Object> response=new HashMap<>();
		SlideGetDTO slide = new SlideGetDTO();
		try {
			slide= slideService.updateSlide(id,slideRequestDTO);
			return ResponseEntity.ok().body(slide);
		 } catch (Exception e) {
			 response.put("mensaje", "Ocurrio un error al actualizar el slide con id: ".concat(id.toString()));
			   response.put("error", e.getMessage());
			   return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.NOT_FOUND);
		 }
			  
	} 


    
}
