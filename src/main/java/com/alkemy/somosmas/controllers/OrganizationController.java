package com.alkemy.somosmas.controllers;


import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.dtos.OrganizationDTO;
import com.alkemy.somosmas.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;


    @GetMapping("/public")
    public ResponseEntity<List<OrganizationBasicDTO>> getAll() {


        List<OrganizationBasicDTO> dtos = organizationService.getAllOrganizations();

        return ResponseEntity.ok().body(dtos);
    }


    @PostMapping("public/{id}")
    /* La historia no indica un id  pero se lo agregue por que para editar necesito saber cual entidad es.
     * ademas no queda claro porque es un post, no seria patch para editar? sino crearia una nueva entidad*/
    public ResponseEntity<OrganizationDTO> update(@PathVariable Long id, @RequestBody OrganizationDTO dto) {


        OrganizationDTO dtoReturned =this.organizationService.update(id, dto);

        return ResponseEntity.ok().body(dtoReturned);
    }




}
