package com.alkemy.somosmas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.somosmas.dtos.LoginUserDTO;
import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.exception.InvalidUserException;
import com.alkemy.somosmas.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/auth/login")
	public ResponseEntity<UserDTO> singIn(@Validated @RequestBody LoginUserDTO loginUserDTO) throws InvalidUserException {
		UserDTO userDTO = userService.authUser(loginUserDTO.getUsername(), loginUserDTO.getPassword());
		return ResponseEntity.ok().body(userDTO);
	}
	@PostMapping("/test")
	public ResponseEntity<Object> test(){
		return ResponseEntity.ok().body(new UserDTO());
	}
}
