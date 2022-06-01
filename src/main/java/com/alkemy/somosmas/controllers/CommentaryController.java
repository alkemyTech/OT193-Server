package com.alkemy.somosmas.controllers;

import java.util.List;

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

import com.alkemy.somosmas.dtos.CommentaryBasicDTO;
import com.alkemy.somosmas.dtos.CommentaryDTO;
import com.alkemy.somosmas.exceptions.CommentException;
import com.alkemy.somosmas.exceptions.InvalidUserException;
import com.alkemy.somosmas.services.CommentaryService;

@RestController
@RequestMapping("/commentary")
public class CommentaryController {
	@Autowired
	CommentaryService service;

    @GetMapping
    public List<CommentaryBasicDTO> getCommentayList(){
    	List<CommentaryBasicDTO> dtoList = service.findAllOrderByCreateDate();
    	return dtoList;
    }
    @GetMapping("/post/{id}")
    public List<CommentaryBasicDTO> getCommentaryByPost(@PathVariable Long id){
    	List<CommentaryBasicDTO> listDTO = service.getByNewsId(id);
    	return listDTO;
    }
    @PostMapping
	public ResponseEntity<CommentaryDTO> save(@Valid @RequestBody CommentaryDTO commentaryDTO){
    	CommentaryDTO comDTO = service.save(commentaryDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(comDTO);
	}
    @PutMapping("/{id}")
    public ResponseEntity<CommentaryDTO> update(@Valid @PathVariable Long idCom, @RequestBody CommentaryDTO dto)throws InvalidUserException,CommentException{
    	CommentaryDTO comDTO = service.update(idCom, dto);
    	return ResponseEntity.ok().body(comDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) throws InvalidUserException,CommentException{
    	service.delete(id);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
