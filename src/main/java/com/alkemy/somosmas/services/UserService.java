package com.alkemy.somosmas.services;

import org.springframework.stereotype.Service;

import com.alkemy.somosmas.models.User;

@Service
public interface UserService  {
	 User findByEmail(String email);
	 Boolean existsByEmail(String email);
}
