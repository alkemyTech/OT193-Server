package com.alkemy.somosmas.ong.services;

import com.alkemy.somosmas.ong.dto.RoleDTO;
import com.alkemy.somosmas.ong.models.Role;
import com.alkemy.somosmas.ong.repositories.RoleRepository;
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
