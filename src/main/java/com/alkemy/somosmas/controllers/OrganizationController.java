package com.alkemy.somosmas.controllers;


import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/public")
    @GetMapping
    public ResponseEntity<List<OrganizationBasicDTO>> getAll() {


        List<OrganizationBasicDTO> dtos = organizationService.getAllOrganizations();

        return ResponseEntity.ok().body(dtos);
    }


}
