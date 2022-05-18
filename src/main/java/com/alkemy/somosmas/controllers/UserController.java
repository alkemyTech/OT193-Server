package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.models.User;
import com.alkemy.somosmas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<UserDTO> getUsersList(){
        List<UserDTO> dtoList = userService.getUsersList();
        return dtoList;
    }
}
