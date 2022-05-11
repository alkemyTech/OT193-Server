package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.ActivityDTO;
import com.alkemy.somosmas.services.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityServiceImpl activityServiceImpl;

    @PostMapping
    public ResponseEntity<ActivityDTO> save(@Valid @RequestBody ActivityDTO activity){
        return ResponseEntity.status(HttpStatus.CREATED).body(activityServiceImpl.save(activity));
    }
}
