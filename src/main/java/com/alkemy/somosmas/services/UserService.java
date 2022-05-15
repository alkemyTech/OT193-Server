package com.alkemy.somosmas.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.exception.InvalidUserException;
import com.alkemy.somosmas.models.User;

@Service
public interface UserService{
	 User findByEmail(String email);
	 Boolean existsByEmail(String email);
	 UserDTO user2UserDTO(User user);
	 UserDTO authUser(String email, String password) throws InvalidUserException;
	 UserDetails loadUserByUsername(String email);
}
