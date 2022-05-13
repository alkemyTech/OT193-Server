package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.dtos.OrganizationDTO;
import com.alkemy.somosmas.models.Organization;

import java.util.List;

public interface OrganizationService {


    public List<OrganizationBasicDTO> getAllOrganizations();

    public OrganizationDTO update (Long id, OrganizationDTO dto);
}
