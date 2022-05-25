package com.alkemy.somosmas.controllers;

<<<<<<< HEAD


import java.util.*;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.dtos.SlidegetSlidesDTO;
import com.alkemy.somosmas.models.Slide;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.dtos.SlideRequestDTO;
import com.alkemy.somosmas.dtos.SlidesGetDTO;
>>>>>>> OT193-60
import com.alkemy.somosmas.services.SlideService;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.http.HttpStatus;
>>>>>>> OT193-60
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Slide")
public class SlideController {
    @Autowired
    SlideService slideService;

    @GetMapping
    public List<SlidegetSlidesDTO> getSlides(){
      return slideService.getSlides();
    }

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

<<<<<<< HEAD
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




=======
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

	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable Long id){
        Map<String, Object> response=new HashMap<>();
		try {
			String msg= slideService.delete(id);
			return ResponseEntity.ok().body(msg);
		 } catch (Exception e) {
			 response.put("mensaje", "Ocurrio un error al eliminar el slide con id: ".concat(id.toString()));
			   response.put("error", e.getMessage());
			   return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.NOT_FOUND);
		 }
	}
	@GetMapping
    public ResponseEntity<?> getAll(){
		Map<String, Object> response=new HashMap<>();    
        try {
			List<SlidesGetDTO>  slidesGet = slideService.getAll();
		   return ResponseEntity.ok().body(slidesGet);
        } catch (Exception e) {
			response.put("mensaje", "Ocurrio un error al listar Slides ");
              response.put("error", e.getMessage());
              return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.NOT_FOUND);
        }
    }


>>>>>>> OT193-60
    
}
