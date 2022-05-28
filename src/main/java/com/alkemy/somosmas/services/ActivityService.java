package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.ActivityDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;

public interface ActivityService {

    ActivityDTO save(ActivityDTO dto) throws ModelNotFoundException;

    ActivityDTO update(Long id, ActivityDTO dto) throws ModelNotFoundException;
}
