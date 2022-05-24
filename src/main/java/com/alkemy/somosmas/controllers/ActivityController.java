package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.ActivityDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.services.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityServiceImpl activityServiceImpl;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ActivityDTO dto){
        ActivityDTO dtoReturned = null;

        try {
            dtoReturned = this.activityServiceImpl.save(dto);
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoReturned);
    }

    @PutMapping
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ActivityDTO dto){
        ActivityDTO dtoReturned = null;
        try {
            dtoReturned = this.activityServiceImpl.update(id, dto);
        } catch (ModelNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(dtoReturned);
    }
}