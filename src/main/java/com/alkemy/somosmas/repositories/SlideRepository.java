package com.alkemy.somosmas.repositories;

import com.alkemy.somosmas.models.Slide;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlideRepository extends JpaRepository<Slide, Long> {
    boolean existsByOrder(int order);
}
