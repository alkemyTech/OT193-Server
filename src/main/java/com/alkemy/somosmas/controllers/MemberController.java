package com.alkemy.somosmas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.exceptions.PageEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<Map<String, Object>> getAll (@RequestParam int page){
		Map<String, Object> response = null;

		try {
			response = this.memberService.getAllMembersByPage(page);
		} catch (NotAcceptableArgumentException e) {
			response= new HashMap<>();
			response.put("Error",e.getMessage());
			return ResponseEntity.ok().body(response);
		} catch (PageEmptyException e) {
			response= new HashMap<>();
			response.put("Error",e.getMessage());
		}

		return ResponseEntity.ok().body(response);
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
