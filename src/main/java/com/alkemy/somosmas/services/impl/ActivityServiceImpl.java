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

    @Override
    public ActivityDTO save(ActivityDTO dto) throws ModelNotFoundException {
        Activity model=null;
        // Si tiene un id lo busco en la base de datos sino lo encuentro lanzo una excepcion.
        if(dto.getId()!=null && dto.getId()!=0){
            model = this.activityRepository.findById(dto.getId()).orElse(null);
            // Esta excepcion arreglarse si eventualmente se usa el metodo.
            if(model==null){
                //Excepcion de tipo check heredar de la clase exception
                throw new ModelNotFoundException(dto.getId(),"Organization");
            }
        }
        model = this.activityMapper.activityDTO2Model(dto);
        Activity modelSaved = activityRepository.save(model);
        ActivityDTO result = activityMapper.activityModel2DTO(modelSaved);

        return result;
    }

    @Override
    public ActivityDTO update(Long id, ActivityDTO dto) throws ModelNotFoundException {
        Activity model = this.activityRepository.findById(id).orElse(null);
        if(model==null){
            //Excepcion de tipo check heredar de la clase exception
            throw new ModelNotFoundException(id,"Activity");
        }
        model= this.activityMapper.organizationRefreshValues(model, dto);
        Activity modelSaved = activityRepository.save(model);
        ActivityDTO result = activityMapper.activityModel2DTO(modelSaved);

        return result;
    }
}