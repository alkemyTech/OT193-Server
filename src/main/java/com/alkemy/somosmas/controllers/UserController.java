package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) throws Exception {
        UserDTO dto = userService.registerUserDTO2Model(userDTO);//trae nombre, apellido, mail y pass
        return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
    }
}
