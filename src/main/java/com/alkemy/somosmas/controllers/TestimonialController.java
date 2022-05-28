package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.TestimonialDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
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

   @DeleteMapping("/{id}")
   public ResponseEntity<String> delete(@PathVariable Long id){
        TestimonialDTO testimonialDTO = null;
        try{
            this.testimonialService.delete(id);
        }catch (ModelNotFoundException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return new ResponseEntity<String>("El Testimonio de id: "+id+" ha sido eliminado.",HttpStatus.OK);
    }
	
	@PutMapping("/{id}")
  public ResponseEntity<Object> updateTestimonial(@RequestBody TestimonialDTO newTestimonialDTO, @PathVariable Long id){
        TestimonialDTO testimonialDTO=null;
        try{
            this.testimonialService.updateTestimonial(newTestimonialDTO,id);
        }catch (ModelNotFoundException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(testimonialDTO);
    }

}
