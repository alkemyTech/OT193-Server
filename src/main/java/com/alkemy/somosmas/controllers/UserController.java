package com.alkemy.somosmas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.somosmas.dtos.LoginUserDTO;
import com.alkemy.somosmas.dtos.UserBasicDTO;
import com.alkemy.somosmas.dtos.UserDTO;
import com.alkemy.somosmas.exceptions.InvalidUserException;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDTO> getUsersList(){
        List<UserDTO> dtoList = userService.getUsersList();
        return dtoList;
    }

    @PostMapping(value = "/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) throws Exception {
        UserDTO dto = userService.registerUserDTO2Model(userDTO);//trae nombre, apellido, mail y pass
        UserBasicDTO dtoResponse = new UserBasicDTO();
        dtoResponse.setFirstName(dto.getFirstName());
        dtoResponse.setLastName(dto.getLastName());
        dtoResponse.setEmail(dto.getEmail());
        return new ResponseEntity<UserBasicDTO>(dtoResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public HttpStatus deleteUser(@PathVariable Long id){
       Boolean deleted  =  this.userService.deleteUser(id);
       if(deleted){
           return HttpStatus.OK;
       }else{
           return HttpStatus.CONFLICT;
       }
    }
    
    @PutMapping("/users/{id}")
	public ResponseEntity<String> update (@Valid @PathVariable Long id, @RequestBody UserDTO dto){
    	UserDTO user = null;
		try {
			user = this.userService.update(id, dto);
		} catch (ModelNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(user.toString());
	}

 	

	@PostMapping("/auth/login")
	public ResponseEntity<LoginUserDTO> singIn(@Validated @RequestBody LoginUserDTO loginUserDTO) throws InvalidUserException {
		LoginUserDTO userDTO = userService.authUser(loginUserDTO.getUsername(), loginUserDTO.getPassword());
		return ResponseEntity.ok().body(userDTO);
	}

	@PostMapping("/test")
	public ResponseEntity<Object> test(){
		return ResponseEntity.ok().body(new UserDTO());
	}
}
