package com.alkemy.somosmas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<NewsDTO> save(@RequestBody NewsDTO news) {
		NewsDTO newsSaved = newsService.save(news);
		return ResponseEntity.status(HttpStatus.CREATED).body(newsSaved);
	}

}
