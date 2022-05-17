package com.alkemy.somosmas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.somosmas.dtos.MemberDTO;
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
	
}
