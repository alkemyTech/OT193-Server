package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.models.User;

import java.util.List;

public interface UserService  {
    Boolean exists(Long id);
    User getUser(Long id);
    List<UserDTO> getUsersList();
}
