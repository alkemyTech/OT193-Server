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

    
}
