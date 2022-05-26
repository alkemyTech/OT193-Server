package com.alkemy.somosmas.mappers;

import com.alkemy.somosmas.dtos.ActivityDTO;
import com.alkemy.somosmas.models.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public Activity activityDTO2Model(ActivityDTO dto){
        Activity activityModel = new Activity();
        activityModel.setNameActivity(dto.getName());
        activityModel.setContentActivity(dto.getContent());
        return activityModel;
    }

    public ActivityDTO activityModel2DTO(Activity activityModel){
        ActivityDTO dto = new ActivityDTO();
        dto.setId(activityModel.getId());
        dto.setName(activityModel.getNameActivity());
        dto.setContent(activityModel.getContentActivity());
        return dto;
    }

    /*Esto apunta a hacer un update solo de lo que se cambio */
    public Activity activityRefreshValues(Activity model, ActivityDTO dto){

        if(dto.getName()!=null && !dto.getName().isEmpty()){
            model.setNameActivity(dto.getName());
        }

        if(dto.getContent()!=null && !dto.getContent().isEmpty()){
            model.setContentActivity(dto.getContent());
        }
        return model;
    }

}
