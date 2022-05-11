package com.alkemy.somosmas.services.impl;

import com.alkemy.somosmas.dtos.ActivityDTO;
import com.alkemy.somosmas.mappers.ActivityMapper;
import com.alkemy.somosmas.models.Activity;
import com.alkemy.somosmas.repositories.ActivityRepository;
import com.alkemy.somosmas.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityMapper activityMapper;

    public ActivityDTO save(ActivityDTO dto){
        Activity activity = activityMapper.activityDTO2Model(dto);
        Activity activitySave = activityRepository.save(activity);
        return activityMapper.activityModel2DTO(activitySave);
    }
}