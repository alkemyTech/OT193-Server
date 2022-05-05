package com.alkemy.somosmas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.somosmas.models.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{
	
}
