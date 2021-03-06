package com.alkemy.somosmas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alkemy.somosmas.dtos.LoginUserDTO;
import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;


import com.alkemy.somosmas.exceptions.InvalidUserException;
import com.alkemy.somosmas.models.User;


@Service
public interface UserService{
    UserDTO getUser(Long id);
    List<UserDTO> getUsersList();
    UserDTO registerUserDTO2Model(UserDTO userDTO) throws Exception;
    Boolean deleteUser(Long id);
	UserDTO update(Long id, UserDTO dto) throws ModelNotFoundException;
    User findByEmail(String email);
	Boolean existsByEmail(String email);
	LoginUserDTO authUser(String email, String password) throws InvalidUserException;
	LoginUserDTO userToDTO(User user);
}
