package com.alkemy.somosmas.services;

import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.models.User;

@Service
public interface UserService  {
	 User findByEmail(String email);
	 Boolean existsByEmail(String email);
	 UserDTO user2UserDTO(User user);
}
