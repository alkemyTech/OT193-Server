package com.alkemy.somosmas.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.models.Role;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.repositories.RoleRepository;

@Component
public class UserMapper {

	@Autowired
	RoleRepository roleRepository;

	public User userDTO2Entity(UserDTO userDTO) {
		Optional<Role> role = roleRepository.findById(userDTO.getRole());
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPhoto(userDTO.getPhoto());
		if(role.isPresent()) {
			user.setRole(role.get());
		}
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
