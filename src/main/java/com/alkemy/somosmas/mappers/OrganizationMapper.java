package com.alkemy.somosmas.mappers;

import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.models.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {



    public OrganizationBasicDTO organizationModel2BasicDto(Organization organization){

        OrganizationBasicDTO dto= new OrganizationBasicDTO();

        dto.setName(organization.getName());
        dto.setImage(organization.getImage());
        dto.setPhone(organization.getPhone());
        dto.setAddress(organization.getAddress());

        return dto;
    }

}
