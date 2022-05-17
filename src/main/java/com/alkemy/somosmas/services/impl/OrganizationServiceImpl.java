package com.alkemy.somosmas.services.impl;

import com.alkemy.somosmas.dtos.OrganizationBasicDTO;
import com.alkemy.somosmas.dtos.OrganizationDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
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

    @Override
    public OrganizationDTO update(Long id, OrganizationDTO dto) throws ModelNotFoundException {
        Organization model = this.organizationRepository.findById(id).orElse(null);

    // Esta excepcion arreglarse si eventualmente se usa el metodo.
        if(model==null){
            //Excepcion de tipo check heredar de la clase exception
            throw new ModelNotFoundException(id,"Organization");
        }
       model= this.organizationMapper.organizationRefreshValues(model, dto);
       Organization modelSaved = organizationRepository.save(model);
       OrganizationDTO result = organizationMapper.organizationModel2Dto(modelSaved);

        return result;
    }

    @Override
    public OrganizationDTO save(OrganizationDTO dto) throws ModelNotFoundException {


        Organization model=null;
        // Si tiene un id lo busco en la base de datos sino lo encuentro lanzo una excepcion.
        if(dto.getId()!=null && dto.getId()!=0){

          model = this.organizationRepository.findById(dto.getId()).orElse(null);

            // Esta excepcion arreglarse si eventualmente se usa el metodo.
            if(model==null){
                //Excepcion de tipo check heredar de la clase exception
                throw new ModelNotFoundException(dto.getId(),"Organization");
            }
        }

        model = this.organizationMapper.organizationDto2Model(dto);
        Organization modelSaved = organizationRepository.save(model);
        OrganizationDTO result = organizationMapper.organizationModel2Dto(modelSaved);
        return result;
    }

}
