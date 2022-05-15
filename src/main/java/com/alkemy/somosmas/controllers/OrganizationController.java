package com.alkemy.somosmas.controllers;


import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.dtos.OrganizationDTO;
import com.alkemy.somosmas.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @PostMapping("public")
    /* La historia no indica un id  pero entiendo podria estar dentro del DTO. Si el objeto existe hace
    * update sino crea uno nuevo*/
    public ResponseEntity<OrganizationDTO> save(@Valid @RequestBody OrganizationDTO dto) {


        OrganizationDTO dtoReturned =this.organizationService.save(dto);

        return ResponseEntity.ok().body(dtoReturned);
    }




    @PutMapping("public/{id}")
  // dejo este metodo que seria para hacer un update en parcial
    public ResponseEntity<OrganizationDTO> update(@PathVariable Long id, @RequestBody OrganizationDTO dto) {


        OrganizationDTO dtoReturned =this.organizationService.update(id, dto);

        return ResponseEntity.ok().body(dtoReturned);
    }




}
