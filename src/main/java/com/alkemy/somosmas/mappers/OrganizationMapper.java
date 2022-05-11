package com.alkemy.somosmas.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.models.Organization;

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


        /* https://www.java67.com/2015/01/java-8-map-function-examples.html*/
        List<OrganizationBasicDTO> dtosList=organizationList
                .stream()
                .map(i->this.organizationModel2BasicDto(i))
                .collect(Collectors.toList());
       /* List<OrganizationBasicDTO> dtosList= new ArrayList<>();
        for(Organization model : organizationList){
            OrganizationBasicDTO dto= this.organizationModel2BasicDto(model);
            dtosList.add(dto);
        }*/

        return dtosList;

    }




}
