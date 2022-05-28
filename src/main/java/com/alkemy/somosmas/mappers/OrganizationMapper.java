package com.alkemy.somosmas.mappers;

import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.dtos.OrganizationDTO;
import com.alkemy.somosmas.models.Organization;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    public OrganizationDTO organizationModel2Dto(Organization organization){

        OrganizationDTO dto= new OrganizationDTO();


        dto.setId(organization.getId());
        dto.setName(organization.getName());
        dto.setImage(organization.getImage());
        dto.setAddress(organization.getAddress());
        dto.setPhone(organization.getPhone());
        dto.setEmail(organization.getEmail());
        dto.setWelcomeText(organization.getWelcomeText());
        dto.setAboutUsText(organization.getAboutUsText());
        dto.setCreateDate(organization.getCreateDate());
        dto.setFacebookUrl(organization.getFacebookUrl());
        dto.setLinkedinUrl(organization.getLinkedinUrl());
        dto.setInstagramUrl(organization.getInstagramUrl());

        return dto;
    }

     /*Esto apunta mas a crear una entidad de cero */
    public Organization organizationDto2Model(OrganizationDTO dto){
      Organization model = new Organization();
      model.setId(dto.getId());
      model.setName(dto.getName());
      model.setImage(dto.getImage());
      model.setAddress(dto.getAddress());
      model.setPhone(dto.getPhone());
      model.setEmail(dto.getEmail());
      model.setWelcomeText(dto.getWelcomeText());
      model.setAboutUsText(dto.getAboutUsText());
      model.setFacebookUrl(dto.getFacebookUrl());
      model.setLinkedinUrl(dto.getLinkedinUrl());
      model.setInstagramUrl(dto.getInstagramUrl());

      return model;
    }

    /*Esto apunta a hacer un update solo de lo que se cambio */
    public Organization organizationRefreshValues(Organization model, OrganizationDTO dto){

        if(dto.getName()!=null && !dto.getName().isEmpty()){
            model.setName(dto.getName());
        }

        if(dto.getImage()!=null && !dto.getImage().isEmpty()){
            model.setImage(dto.getImage());
        }

        if (dto.getAddress()!=null && !dto.getAddress().isEmpty()){
            model.setAddress(dto.getAddress());
        }

        if (dto.getPhone()!=0){
            model.setPhone(dto.getPhone());
        }

        if (dto.getEmail()!=null && !dto.getEmail().isEmpty()){
            model.setEmail(dto.getEmail());
        }

        if (dto.getWelcomeText()!=null && !dto.getWelcomeText().isEmpty()){
            model.setWelcomeText(dto.getWelcomeText());
        }

        if (dto.getAboutUsText()!=null && !dto.getAboutUsText().isEmpty()){
            model.setAboutUsText(dto.getAboutUsText());
        }

        if (dto.getFacebookUrl()!=null && !dto.getFacebookUrl().isEmpty()){
            model.setFacebookUrl(dto.getFacebookUrl());
        }

        if (dto.getLinkedinUrl()!=null && !dto.getLinkedinUrl().isEmpty()){
            model.setLinkedinUrl(dto.getLinkedinUrl());
        }

        if (dto.getInstagramUrl()!=null && !dto.getInstagramUrl().isEmpty()){
            model.setInstagramUrl(dto.getInstagramUrl());
        }



        /*No agrego deleted y create date porque no estan relacionados a esto */

        return model;
    }



}
