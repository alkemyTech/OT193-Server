package com.alkemy.somosmas.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.mapper.UserMapper;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.UserRepository;
import com.alkemy.somosmas.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	/*@Override
	public User save(User dto) {
		User user = userMapper.userDTO2Entity(dto);
		User userSave = userRepository.save(user);
		UserDTO result = userMapper.entityToUserDTO(userSave);
		return result;
	}*/

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}



}
