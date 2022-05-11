package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dto.ActivityDTO;
import com.alkemy.somosmas.models.Activity;
import com.alkemy.somosmas.repositories.ActivityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    /*
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ObjectMapper mapper;
*/
    public ActivityDTO save(ActivityDTO dto){
       /* Activity activity = mapper.convertValue(dto,Activity.class);
        activityRepository.save(activity);
        */
        System.out.println("save activity");
        return dto;//mapper.convertValue(activity,ActivityDTO.class);
    }
}