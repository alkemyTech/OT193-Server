package com.alkemy.somosmas.controllers;


import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.dtos.OrganizationDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
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
    /*Tuve que devolver un generico para que envie el error sino debia enviarlo con un dto */
    public ResponseEntity<Object> save(@Valid @RequestBody OrganizationDTO dto)  {


        OrganizationDTO dtoReturned = null;
        try {
            dtoReturned = this.organizationService.save(dto);
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());

            /* opcion 1 con dto
            OrganizationDTO dtoError= new OrganizationDTO();
            dtoError.setName(e.getMessage());
            return ResponseEntity.badRequest().body(dtoError);*/
            //Opcion2
             return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(dtoReturned);
    }




    @PutMapping("public/{id}")
  // dejo este metodo que seria para hacer un update en parcial
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody OrganizationDTO dto) {


        OrganizationDTO dtoReturned = null;
        try {
            dtoReturned = this.organizationService.update(id, dto);
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(dtoReturned);
    }




}
