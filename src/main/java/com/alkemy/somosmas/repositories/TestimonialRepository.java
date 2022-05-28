package com.alkemy.somosmas.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alkemy.somosmas.models.Testimonial;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends CrudRepository<Testimonial, Long> {

}
