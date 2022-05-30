package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;

import java.util.List;


public interface UserService  {
    UserDTO getUser(Long id);
    List<UserDTO> getUsersList();
    UserDTO registerUserDTO2Model(UserDTO userDTO) throws Exception;
    Boolean deleteUser(Long id);
	UserDTO update(Long id, UserDTO dto) throws ModelNotFoundException;
}
