package com.alkemy.somosmas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.mappers.UserMapper;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDTO getUser(Long id) {
		if(this.userRepository.existsById(id)){
			User userNew = this.userRepository.getById(id);
			UserDTO userDTO = userMapper.originalToDTO(userNew);
			return userDTO;
		}
		else{
			return null;
		}
	}

	@Override
	public List<UserDTO> getUsersList() {
		List<User> model = userRepository.findAll();
		List<UserDTO> dtoList = userMapper.modelUserToUserDTO(model);
		return dtoList;
	}

	@Override
	public UserDTO registerUserDTO2Model(UserDTO userDTO) throws Exception {
		Boolean mailExists = this.userRepository.existsByEmail(userDTO.getEmail());
		if(!mailExists){
			User newUser = userMapper.dto2Model(userDTO);
			this.userRepository.save(newUser);
			return userDTO;
		}else{
			throw new Exception("MAIL EXISTENTE, ELIJA OTRO POR FAVOR.");
		}
	}


	@Override
	public Boolean deleteUser(Long id) {
		if(this.userRepository.existsById(id)){
			User user = this.userRepository.getById(id);
			user.setDeleted(Boolean.TRUE);
			this.userRepository.save(user);
			return true;
		}else{
			return false;
		}
	}




}
