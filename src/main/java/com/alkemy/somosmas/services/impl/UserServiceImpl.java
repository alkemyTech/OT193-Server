package com.alkemy.somosmas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.exception.InvalidUserException;
import com.alkemy.somosmas.mappers.UserMapper;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.UserRepository;
import com.alkemy.somosmas.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	private void injectUserInSecurityContext(UserDTO userDTO) {
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(),userDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public UserDTO authUser(String email, String password) throws InvalidUserException {
		if(!existsByEmail(email)) {
			throw new InvalidUserException("Email does not exist");
		}
		User user = findByEmail(email);
		if(!passwordEncoder.matches(password,user.getPassword())) {
			throw new InvalidUserException("Incorrect email or password");
		}
		UserDTO userDTO = user2UserDTO(user);
		injectUserInSecurityContext(userDTO);
		return userDTO;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserDTO user2UserDTO(User user) {
		UserDTO userDTO = userMapper.userToUserDTO(user);
		return userDTO;
	}



}
