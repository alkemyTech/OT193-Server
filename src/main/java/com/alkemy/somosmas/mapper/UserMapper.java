package com.alkemy.somosmas.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.somosmas.dto.UserDTO;
import com.alkemy.somosmas.models.User;

@Component
public class UserMapper {

	public User userDTO2Entity(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPhoto(userDTO.getPhoto());
		return user;
	}

	public UserDTO entityToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setPhoto(user.getPhoto());
		return userDTO;
	}

	public List<UserDTO> userEntityListToDTOList(List<User> users) {
		List<UserDTO> dtos = new ArrayList<>();
		for(User user : users) {
			dtos.add(entityToUserDTO(user));
		}
		return  dtos;
	}
}
