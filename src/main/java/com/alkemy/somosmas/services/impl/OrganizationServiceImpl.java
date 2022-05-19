package com.alkemy.somosmas.services.impl;

import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.mappers.OrganizationMapper;
import com.alkemy.somosmas.models.Organization;
import com.alkemy.somosmas.repositories.OrganizationRepository;
import com.alkemy.somosmas.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private OrganizationMapper organizationMapper;





    @Override
    public List<OrganizationBasicDTO> getAllOrganizations() {

        List<Organization> models = organizationRepository.findAll();
        List<OrganizationBasicDTO> dtos = organizationMapper.organizationModelList2BasicDtoList(models);
        return dtos;
    }
}
