package com.alkemy.somosmas.services;

import java.util.List;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.exception.InvalidUserException;
import com.alkemy.somosmas.models.User;

public interface UserService  {
    UserDTO getUser(Long id);
    List<UserDTO> getUsersList();
    UserDTO registerUserDTO2Model(UserDTO userDTO) throws Exception;
    Boolean deleteUser(Long id);
    User findByEmail(String email);
	Boolean existsByEmail(String email);
	UserDTO authUser(String email, String password) throws InvalidUserException;
}
