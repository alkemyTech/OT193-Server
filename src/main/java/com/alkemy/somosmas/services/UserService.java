package com.alkemy.somosmas.services;

import java.util.List;

import com.alkemy.somosmas.dtos.UserDTO;

public interface UserService  {
    UserDTO getUser(Long id);
    List<UserDTO> getUsersList();
    UserDTO registerUserDTO2Model(UserDTO userDTO) throws Exception;
    Boolean deleteUser(Long id);

}
