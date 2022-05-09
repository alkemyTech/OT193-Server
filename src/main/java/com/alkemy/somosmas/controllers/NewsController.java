package com.alkemy.somosmas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.somosmas.dto.NewsDTO;
import com.alkemy.somosmas.services.NewsService;

@RestController
@RequestMapping("news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@PostMapping
	public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO news) {
		NewsDTO newsSaved = newsService.save(news);
		return ResponseEntity.status(HttpStatus.CREATED).body(newsSaved);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
		this.newsService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NewsDTO> getDetailsById(@Valid @PathVariable Long id){
		NewsDTO newsDto = this.newsService.getDetailsById(id);
		return ResponseEntity.ok(newsDto);
	}
	
}
