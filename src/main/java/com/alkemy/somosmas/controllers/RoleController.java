package com.alkemy.somosmas.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.somosmas.models.Role;
import com.alkemy.somosmas.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleService;

	@GetMapping(path = "/{id}")
	public Optional<Role> getRoleById(@PathVariable("id") Long id){
		return roleService.findById(id);
	}
}
