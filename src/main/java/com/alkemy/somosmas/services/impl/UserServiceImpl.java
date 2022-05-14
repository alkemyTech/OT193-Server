package com.alkemy.somosmas.services.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.exception.SomosmasException;
import com.alkemy.somosmas.mappers.UserMapper;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.UserRepository;
import com.alkemy.somosmas.services.UserService;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserMapper userMapper;

	public UserDTO authUser(String email, String password) {
		if(!existsByEmail(email)) {
			throw new SomosmasException("Email does not exist");
		}
		User user = findByEmail(email);
		if(!passwordEncoder.matches(password,user.getPassword())) {
			throw new SomosmasException("Incorrect password");
		}
		return user2UserDTO(user);
	}

  	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        java.util.Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), grantedAuthorities);
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
