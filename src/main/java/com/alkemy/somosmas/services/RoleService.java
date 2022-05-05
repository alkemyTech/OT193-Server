package com.alkemy.somosmas.services;

import com.alkemy.somosmas.DTO.RoleDTO;
import com.alkemy.somosmas.models.Role;
import com.alkemy.somosmas.repositories.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

@Autowired
private RoleRepository roleRepository;

@Autowired
private ObjectMapper mapper;
    public RoleDTO save(RoleDTO dto){
        Role role = mapper.convertValue(dto,Role.class);
        roleRepository.save(role);
        RoleDTO roleDTO = mapper.convertValue(role,RoleDTO.class);
        System.out.println("guardar role");
        return roleDTO;
    }

}
