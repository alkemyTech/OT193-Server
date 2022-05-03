package com.alkemy.somosmas.ong.controllers;

import com.alkemy.somosmas.ong.dto.RoleDTO;
import com.alkemy.somosmas.ong.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> save(@RequestBody RoleDTO role){
        RoleDTO roleGuardado = roleService.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleGuardado);
    }
}
