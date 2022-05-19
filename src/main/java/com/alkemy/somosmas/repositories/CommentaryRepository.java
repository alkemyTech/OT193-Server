package com.alkemy.somosmas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.somosmas.models.Commentary;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long>{

}
