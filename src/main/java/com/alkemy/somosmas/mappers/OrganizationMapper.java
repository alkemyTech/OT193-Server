package com.alkemy.somosmas.mappers;

import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.models.Organization;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<OrganizationBasicDTO> organizationModelList2BasicDtoList(List<Organization> organizationList){

        List<OrganizationBasicDTO> dtosList= new ArrayList<>();

        for(Organization model : organizationList){

            OrganizationBasicDTO dto= this.organizationModel2BasicDto(model);
            dtosList.add(dto);
        }

        return dtosList;

    }




}
