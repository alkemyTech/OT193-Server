package com.alkemy.somosmas.services;

import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.exceptions.PageEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.TestimonialDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.models.Testimonial;
import com.alkemy.somosmas.repositories.TestimonialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TestimonialServiceImpl implements TestimonialService {
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

    public void delete(Long id) throws ModelNotFoundException{
        Testimonial model = this.testimonialRepository.findById(id).orElse(null);
        if(model == null){
            //Excepcion de tipo check heredar de la clase exception
            throw new ModelNotFoundException(id,"Testimonial");
        }
        this.testimonialRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> getAllTestimonialsByPage(int pageNo) throws NotAcceptableArgumentException, PageEmptyException {

        if(pageNo<0){
            throw new NotAcceptableArgumentException("The page number must be positive");
        }
        Pageable pageable = PageRequest.of(pageNo,10);

        Page<Testimonial> allTestimonialsPage= testimonialRepository.findAll(pageable);

        if(allTestimonialsPage.isEmpty()){
            throw new PageEmptyException(pageNo, "testimonials");
        }


        List<Testimonial> testimonialsModel = allTestimonialsPage.getContent();

        List<TestimonialDTO> testimonialDtoReturned = testimonialsModel
                .stream()
                .map(i->this.mapper.convertValue(i,TestimonialDTO.class))
                .collect(Collectors.toList());


        Map<String, Object> returnedMap = new HashMap<>();
        returnedMap.put("Testimonials", testimonialDtoReturned);
        returnedMap.put("currentPage",allTestimonialsPage.getNumber());
        returnedMap.put("totalItems",allTestimonialsPage.getTotalElements());
        returnedMap.put("totalPages",allTestimonialsPage.getTotalPages());

        if (allTestimonialsPage.hasNext()){
            returnedMap.put("nextPage","localhost:8080/testimonial?page="+(pageNo+1));
        }
        if (pageNo!=0){
            returnedMap.put("previousPage","localhost:8080/testimonial?page="+(pageNo-1));
        }

        return returnedMap;
    }


}
