package com.alkemy.somosmas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.somosmas.dtos.NewsDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.services.NewsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Operation(summary = "Create News")
//	@ApiResponses(value = { 
//			  @ApiResponse(responseCode = "201", description = "News Created"), 
//			  @ApiResponse(responseCode = "400", description = "Invalid model", 
//			    content = @Content), 
//			  })
	@PostMapping
	public ResponseEntity<Object> save(@Valid @RequestBody NewsDTO news) {
		NewsDTO newsSaved = null;
		try {
			newsSaved = newsService.save(news);
		} catch (ModelNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newsSaved);
	}

	@Operation(summary = "Delete News by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "News deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            })
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

	@Operation(summary = "Find News by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News details"),
            @ApiResponse(responseCode = "400", description = "News not found", content = @Content)})
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

	@Operation(summary = "Update News by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "News updated"),
            @ApiResponse(responseCode = "400", description = "News not found", content = @Content)})
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
