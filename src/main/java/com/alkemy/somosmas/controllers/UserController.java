package com.alkemy.somosmas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.somosmas.auth.JwtUtils;
import com.alkemy.somosmas.dto.AuthenticationRequest;
import com.alkemy.somosmas.dto.AuthenticationResponse;
import com.alkemy.somosmas.services.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder encoder;
	private JwtUtils jwtTokenUtil;


	 @PostMapping("/auth/login")
     public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest) throws Exception{
         UserDetails userDetails;
         try{
             Authentication auth= authenticationManager.authenticate(

                     new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
             );
             userDetails= (UserDetails) auth.getPrincipal();
         } catch (BadCredentialsException e){
             throw new BadCredentialsException("Incorrect username or password", e);
     }
         final String jwt = jwtTokenUtil.generateToken(userDetails);
         return ResponseEntity.ok(new AuthenticationResponse(jwt));
 }



}
