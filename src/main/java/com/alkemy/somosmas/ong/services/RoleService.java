package com.alkemy.somosmas.ong.services;

import com.alkemy.somosmas.ong.dto.RoleDTO;
import com.alkemy.somosmas.ong.models.Role;
import com.alkemy.somosmas.ong.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

@Autowired
private RoleRepository roleRepository;
    public RoleDTO save(RoleDTO dto){
        Role entity = new Role();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        Role entitySaved = this.roleRepository.save(entity);

        RoleDTO dto2 = new RoleDTO();
        dto2.setId(entitySaved.getId());
        dto2.setName(entitySaved.getName());
        dto2.setDescription(entitySaved.getDescription());


        System.out.println("guardar role");
        return dto2;
    }

}
