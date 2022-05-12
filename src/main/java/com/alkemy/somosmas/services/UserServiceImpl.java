package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.mappers.UserMapper;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService ,UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	/*@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}*/


	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return null;
	}

	@Override
	public Boolean exists(Long id) {
		Boolean exists = this.userRepository.existsById(id);
		if(exists){
			return exists;
		}else{
			return false;
		}
	}

	@Override
	public User getUser(Long id) {
		if(exists(id)){
			User user = new User();
			user = this.userRepository.getById(id);
			return user;
		}else{
			return null;
		}
	}

	@Override
	public List<UserDTO> getUsersList() {
		List<User> model = userRepository.findAll();
		List<UserDTO> dtoList = userMapper.modelUserToUserDTO(model);
		return dtoList;
	}
}
