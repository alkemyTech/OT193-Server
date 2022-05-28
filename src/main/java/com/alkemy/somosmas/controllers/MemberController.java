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

import com.alkemy.somosmas.dtos.MemberDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.services.MemberService;

@RestController
@RequestMapping("members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping
	public ResponseEntity<MemberDTO> save (@Valid @RequestBody MemberDTO memberDTO){
		MemberDTO memberSaved = this.memberService.save(memberDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(memberSaved);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete (@Valid @PathVariable Long id){
		try {
			this.memberService.delete(id);;
		} catch (ModelNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping
	public ResponseEntity<List<MemberDTO>> getAll(){
		List<MemberDTO> membersDTO = this.memberService.getAll();
		return ResponseEntity.ok().body(membersDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@Valid @PathVariable Long id, @RequestBody MemberDTO memberDTO){
		MemberDTO member = null;
		try {
			member = this.memberService.update(id, memberDTO);
		} catch (ModelNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().body(member);
	}
}
