package com.alkemy.somosmas.repositories;

import java.util.List;

import com.alkemy.somosmas.models.Slide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {
    boolean existsByOrder(int order);
}
