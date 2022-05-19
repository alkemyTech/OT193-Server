package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.ActivityDTO;

public interface ActivityService {

    ActivityDTO save(ActivityDTO dto);

    ActivityDTO update(Long id, ActivityDTO dto) throws ModelNotFoundException;
}
