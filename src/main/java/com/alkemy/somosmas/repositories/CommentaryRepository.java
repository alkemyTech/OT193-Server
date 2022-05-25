package com.alkemy.somosmas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.somosmas.models.Commentary;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long>{
	List<Commentary> findAllByOrderByCreateDateAsc();
	List<Commentary> findAllByNewsId(Long id);
}
