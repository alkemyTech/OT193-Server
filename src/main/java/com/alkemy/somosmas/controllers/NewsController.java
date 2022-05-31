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
import com.alkemy.somosmas.models.News;
import com.alkemy.somosmas.services.NewsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Operation(summary = "Create News", responses = {
			@ApiResponse(description = "News Created", responseCode = "201",
					content=@Content(mediaType="application/json",schema =@Schema(implementation=News.class))),
			@ApiResponse(description = "Invalid model",responseCode = "400",content = @Content)
	})
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
