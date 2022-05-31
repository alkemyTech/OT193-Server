package com.alkemy.somosmas.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.dtos.SlideRequestDTO;
import com.alkemy.somosmas.dtos.SlidesGetDTO;
import com.alkemy.somosmas.exceptions.ModelNotFoundException;
import com.alkemy.somosmas.exceptions.NotAcceptableArgumentException;
import com.alkemy.somosmas.mappers.OrganizationMapper;
import com.alkemy.somosmas.mappers.SlideMapper;

import com.alkemy.somosmas.models.Slide;
import com.alkemy.somosmas.repositories.OrganizationRepository;
import com.alkemy.somosmas.repositories.SlideRepository;
import com.alkemy.somosmas.services.AmazonClient;
import com.alkemy.somosmas.services.SlideService;
import com.alkemy.somosmas.utils.Base64MultipartFile;
import com.alkemy.somosmas.utils.ImageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SlideServiceImpl implements SlideService{
    @Autowired
    SlideMapper slideMapper;

    @Autowired
    OrganizationMapper organizationMapper;

    @Autowired
    SlideRepository slideRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    AmazonClient amazonClient;

    
    Base64MultipartFile base64MultipartFile;

    @Autowired
    ImageHelper imageHelper;
    @Override
    public SlideGetDTO create(SlideRequestDTO slideDTO) throws  NotAcceptableArgumentException, IOException {
       					
            if(slideDTO.getOrder()==null){
				try {
					Integer ultOrder=(slideRepository.findAll().get(slideRepository.findAll().size()-1).getOrder())+1;
                    slideDTO.setOrder(ultOrder);
            	 }catch (IndexOutOfBoundsException e) {
						//Lista vacia, se asigna el valor de uno a order
					slideDTO.setOrder(1);
				}         			
		    } 
            //validacion de inputs
            if(slideRepository.existsByOrder(slideDTO.getOrder())) {
				//Orden ya existe
				throw new NotAcceptableArgumentException("Order already exists on the slide.");					   			
            }else if(slideDTO.getOrganizationId()==0){
				throw new NotAcceptableArgumentException("You must enter a non-zero value for the Id organization field");
			}else if(!organizationRepository.existsById(slideDTO.getOrganizationId())){
				//validacion si organizacion existe
				throw new NotAcceptableArgumentException("Organization doesn't exist ");
			}else{
				String nameFile = "Slide_"+slideDTO.getOrganizationId()+slideDTO.getOrder()+".png";
				MultipartFile multiparte =imageHelper.base64ToImage(slideDTO.getEncodeImg(),nameFile);
				String amazonUrl=amazonClient.uploadFile(multiparte);       
				slideDTO.setImageUrl(amazonUrl);
        				
				//cambio de DTO slide request a DTO basico
				SlideDTO slideD=new SlideDTO();
				slideD.setImageUrl(slideDTO.getImageUrl());
				slideD.setOrder(slideDTO.getOrder());
				slideD.setOrganization(organizationRepository.findById(slideDTO.getOrganizationId()).get());
				slideD.setText(slideDTO.getText());
				Slide slide = slideMapper.slideDtoToEntity(slideD);
				slideRepository.save(slide);
				SlideGetDTO slideDto = slideMapper.slideEntityDto(slide);
	       		 return slideDto;
			  }
        
    }

    @Override
    public String delete(Long id) throws ModelNotFoundException  {
		
        if(slideRepository.existsById(id)){
			slideRepository.deleteById(id);
			return "Slide with the id "+id+" was deleted.";
		}else{
			throw new ModelNotFoundException(id,"Slide");
		}
    }

    @Override
    public SlideGetDTO getSlide(Long id) throws ModelNotFoundException {

        if(slideRepository.existsById(id)){
           // Optional<Slide> slide= slideRepository.findById(id);
            SlideGetDTO slideDTO= slideMapper.getSlide(slideRepository.findById(id).get());       
            return slideDTO;
		}else{
			throw new ModelNotFoundException(id,"Slide");
		}
       
    }

    @Override
    public SlideGetDTO updateSlide(Long id, SlideRequestDTO slideRequestDTO) throws ModelNotFoundException, NotAcceptableArgumentException, IOException {
        Long organizationId= slideRequestDTO.getOrganizationId();

		if(slideRepository.existsById(id)){	
			Slide s= slideRepository.findById(id).get();	
			SlideDTO slideDTO=new SlideDTO(s.getId(),s.getImageUrl(),s.getOrder(),s.getText(),
			s.getOrganization());

			if(!(slideRequestDTO.getOrder()==null)){
				if(slideRepository.existsByOrder(slideRequestDTO.getOrder())){
					throw new  NotAcceptableArgumentException("Order already exists on the slide");
				}else{						 
					slideDTO.setOrder(slideRequestDTO.getOrder());
				}
			}
			if(!(organizationId==0)){
				if(organizationRepository.existsById(slideRequestDTO.getOrganizationId())){
				slideDTO.setOrganization(organizationRepository.findById(slideRequestDTO.getOrganizationId()).get());
				}else{
					throw new  NotAcceptableArgumentException("Organization doesn't exist ");
				}
			}
			if(!(slideRequestDTO.getText()==null)){
			
				slideDTO.setText(slideRequestDTO.getText());				
			}
			if(!(slideRequestDTO.getEncodeImg()==null)){
				
				String nameFile = "Slide_"+slideDTO.getOrganization().getId()+slideDTO.getOrder()+".png";
				MultipartFile multiparte =imageHelper.base64ToImage(slideRequestDTO.getEncodeImg(),nameFile);
				amazonClient.deleteFileFromS3Bucket(slideDTO.getImageUrl());
				String amazonUrl=amazonClient.uploadFile(multiparte);       
				slideDTO.setImageUrl(amazonUrl);
			}

			Slide slideUpdated = slideMapper.slideDtoToEntity(slideDTO);
			slideUpdated.setId(slideDTO.getId());
			slideRepository.save(slideUpdated);
			SlideGetDTO slideDto = slideMapper.slideEntityDto(slideUpdated);
			return slideDto;

		} else{
			throw new ModelNotFoundException(id,"Slide");
		}
    }

    @Override
    public List<SlidesGetDTO> getAllSlides(){
        List<Slide> slides = slideRepository.findAll();	
		List<SlidesGetDTO> getSlides = new ArrayList<>();
		for(Slide entity : slides){
			getSlides.add(slideMapper.getSlides(entity,new SlidesGetDTO()));
		}	
		return getSlides;
		
    }






    
}
