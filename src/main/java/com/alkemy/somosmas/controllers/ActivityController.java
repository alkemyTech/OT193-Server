package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dto.ActivityDTO;
import com.alkemy.somosmas.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDTO> save(@RequestBody ActivityDTO activity){
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.save(activity));
    }
}
