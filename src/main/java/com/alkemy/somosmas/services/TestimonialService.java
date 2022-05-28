package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.TestimonialDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;

public interface TestimonialService {

    public TestimonialDTO save(TestimonialDTO dto);
    public TestimonialDTO updateTestimonial(TestimonialDTO newTestimonialDTO, Long id) throws ModelNotFoundException;
	public void delete(Long id) throws ModelNotFoundException;

}