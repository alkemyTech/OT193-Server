package com.alkemy.somosmas.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.dtos.SlideRequestDTO;
import com.alkemy.somosmas.dtos.SlidesGetDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.services.SlideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Slide")
//@RestControllerAdvice
public class SlideController {
    @Autowired
    SlideService slideService;

    @PostMapping
	public ResponseEntity<?> create( @Valid @RequestBody SlideRequestDTO slideDTO) {
		SlideGetDTO slide = new SlideGetDTO();
		try {
			slide= this.slideService.create(slideDTO);		
		 } catch (NotAcceptableArgumentException e) {
			 return ResponseEntity.badRequest().body(e.getMessage());
		 }catch(IOException e){
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		 }
			return ResponseEntity.ok().body(slide);	
	}

    
	@GetMapping("/{id}")
    public ResponseEntity<?> getSlide(@PathVariable Long id) {
		Map<String, Object> response=new HashMap<>();
        SlideGetDTO slide = new SlideGetDTO();
        try {
           slide= slideService.getSlide(id);
		   response.put("Slide", slide);
		  // return ResponseEntity.ok().body(slide);
        } catch (ModelNotFoundException e) {
			response.put("mensaje", "Ocurrio un error al mostrar el slide con id: ".concat(id.toString()));
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")  
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SlideRequestDTO slideRequestDTO)   
	{  
		Map<String, Object> response=new HashMap<>();
		SlideGetDTO slide = new SlideGetDTO();
		try {
			slide= slideService.updateSlide(id,slideRequestDTO);
			response.put(null,slide);			
		 } catch (ModelNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		 }catch( NotAcceptableArgumentException | IOException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		 }
		 return ResponseEntity.ok().body(slide);
			  
	} 

	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable Long id){
       // Map<String, Object> response=new HashMap<>();
		try {
			String msg= slideService.delete(id);
			return ResponseEntity.ok().body(msg);
		 } catch (ModelNotFoundException e) {
			/*response.put("mensaje", "Ocurrio un error al eliminar el slide con id: ".concat(id.toString()));
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.NOT_FOUND);*/
			return ResponseEntity.badRequest().body(e.getMessage());
		 }
	}
	@GetMapping
    public ResponseEntity<?> getAllSlides(){
		/*Map<String, Object> response=new HashMap<>();    
        try {*/
			List<SlidesGetDTO>  getAllSlides = slideService.getAllSlides();
		   return ResponseEntity.ok().body(getAllSlides);
       /* } catch (Exception e) {
			response.put("mensaje", "Ocurrio un error al listar Slides ");
              response.put("error", e.getMessage());
              return new ResponseEntity<Map<String, Object>>(response, null, HttpStatus.NO_CONTENT);
        }*/
    }
}
