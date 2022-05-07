package com.alkemy.somosmas.controllers;


import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {


    @RequestMapping("/public")
    @GetMapping
    public ResponseEntity<List<OrganizationBasicDTO>> getAll() {


        List<OrganizationBasicDTO> dtos = new ArrayList<>() {
        };

        return ResponseEntity.ok().body(dtos);
    }


}
