package com.alkemy.somosmas.controllers;

import javax.validation.Valid;

import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.exceptions.PageEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.somosmas.dtos.NewsDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.services.NewsService;

import java.util.HashMap;
import java.util.Map;

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
	public ResponseEntity<Object> delete(@Valid @PathVariable Long id) {
		try {
			this.newsService.delete(id);
		} catch (ModelNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getDetailsById(@Valid @PathVariable Long id){
		NewsDTO newsDto = null;
		try {
			newsDto = this.newsService.getDetailsById(id);
		} catch (ModelNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok(newsDto);
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAll (@RequestParam int page){
		Map<String, Object> response = null;

		try {
			response = this.newsService.getAllNewsByPage(page);
		} catch (NotAcceptableArgumentException e) {
			response= new HashMap<>();
			response.put("Error",e.getMessage());
			return ResponseEntity.badRequest().body(response);
		} catch (PageEmptyException e) {
			response= new HashMap<>();
			response.put("Error",e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok().body(response);
	}

	@PutMapping("{id}")
	public ResponseEntity<Object> update (@Valid @PathVariable Long id, @RequestBody NewsDTO dto){
		NewsDTO news = null;
		try {
			news = this.newsService.update(id, dto);
		} catch (ModelNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().body(news);
	}

}
