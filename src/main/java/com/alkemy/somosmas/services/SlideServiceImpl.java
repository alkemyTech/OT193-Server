package com.alkemy.somosmas.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.dtos.SlideGetDTO;
import com.alkemy.somosmas.dtos.SlideRequestDTO;
import com.alkemy.somosmas.dtos.SlidesGetDTO;
import com.alkemy.somosmas.mappers.OrganizationMapper;
import com.alkemy.somosmas.mappers.SlideMapper;
import com.alkemy.somosmas.models.Organization;
import com.alkemy.somosmas.models.Slide;
import com.alkemy.somosmas.repositories.OrganizationRepository;
import com.alkemy.somosmas.repositories.SlideRepository;
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
    public SlideGetDTO create(SlideRequestDTO slideDTO) throws IOException, Exception {
        List<Slide> listSlide=  slideRepository.findAll();
		 Long organizationId= slideDTO.getOrganizationId();
		 boolean existOrganization= organizationRepository.existsById(organizationId);
		try {
			Slide ultSlide=listSlide.get(listSlide.size()-1);
			if(slideDTO.getOrder_ong()==null){
				//order es null se asigna el valor inmediato al ultimo registro			
	            Integer ultOrder=  ultSlide.getOrder();
	                ultOrder++;
	                slideDTO.setOrder_ong(ultOrder);
			}else if(existSlideByOrder(slideDTO.getOrder_ong())) {
				//Orden ya existe
				throw new Exception("Order ya existe en el slide.");			
		   }			
		} catch (IndexOutOfBoundsException e) {
			  //Lista vacia, se asigna el valor de uno a order
			  System.out.println( "Lista de slide vacia se asigna el valor de uno a order: "+ e );
			  slideDTO.setOrder_ong(1);
			}
		 				
		 	if(slideDTO.getEncodeImg()== null){
				//Input vacio del campo imageEncode
				throw new Exception("No se ingreso la imagen en Base64");	  
			}else if(organizationId==0){
				throw new Exception("Debe ingresar un valor no nulo para el campo Id organization");
			}else if(!existOrganization){
				//validacion si organizacion existe
				throw new Exception("La organizacion no existe ");
			}else{
				String nameFile = "Slide_"+slideDTO.getOrganizationId()+slideDTO.getOrder_ong()+".png";
				MultipartFile multiparte =imageHelper.base64ToImage(slideDTO.getEncodeImg(),nameFile);
				String amazonUrl=amazonClient.uploadFile(multiparte);       
				slideDTO.setImageUrl(amazonUrl);
            
				//cambio de DTO slide request a DTO basico
				SlideDTO slideD=new SlideDTO();
				slideD.setImageUrl(slideDTO.getImageUrl());
				slideD.setOrder(slideDTO.getOrder_ong());
				slideD.setOrganization(getOrganizationId(organizationId));
				slideD.setText(slideDTO.getText());
				Slide slide = slideMapper.slideDtoToEntity(slideD);
				slideRepository.save(slide);
				SlideGetDTO slideDto = slideMapper.slideEntityDto(slide);
	       		 return slideDto;
			  }
        
    }

    public Organization getOrganizationId(Long id) {
		Optional<Organization> organization = organizationRepository.findById(id);
		return organization.get();
	}


	public boolean existOrganizationById(Long id){
		return organizationRepository.existsById(id);
	}

	public boolean existSlideById(Long id){
		return slideRepository.existsById(id);
	}

	public boolean existSlideByOrder(Integer order){
		return slideRepository.existsByOrder(order);
	}

    @Override
    public String delete(Long id) throws Exception {
        if(existSlideById(id)){
			slideRepository.deleteById(id);
			return "Se elemino el slide con el id:"+id;
		}else{
			throw new Exception("Slide no existe ");
		}
        
    }

    @Override
    public SlideGetDTO getSlide(Long id) {
        Optional<Slide> slide= slideRepository.findById(id);
        SlideGetDTO slideDTO= slideMapper.getSlide(slide.get());       
        return slideDTO;
	
    }


    @Override
    public SlideGetDTO updateSlide(Long id, SlideRequestDTO slideRequestDTO) throws Exception {
        Long l= slideRequestDTO.getOrganizationId();

		if(existSlideById(id)){
			Optional<Slide> slide= slideRepository.findById(id);

			Slide s= slide.get();

			SlideDTO slideD=new SlideDTO(s.getId(),s.getImageUrl(),s.getOrder(),s.getText(),s.getOrganization());

			if(!(slideRequestDTO.getOrder_ong()==null)){

				if(existSlideByOrder(slideRequestDTO.getOrder_ong())){
					throw new Exception("Order ya existe en el slide.");
				}else{
					slideD.setOrder(slideRequestDTO.getOrder_ong());
				}
			}
			if(!(l==0)){
				if(organizationRepository.existsById(slideRequestDTO.getOrganizationId())){
					slideD.setOrganization(getOrganizationId(slideRequestDTO.getOrganizationId()));
				}else{
					throw new Exception("La Organizacion no existe ");
				}
			}
			if(!(slideRequestDTO.getText()==null)){
			
				slideD.setText(slideRequestDTO.getText());				
			}
			if(!(slideRequestDTO.getEncodeImg()==null)){
				
				String nameFile = "Slide_"+slideD.getOrganization().getId()+slideD.getOrder()+".png";
				MultipartFile multiparte =imageHelper.base64ToImage(slideRequestDTO.getEncodeImg(),nameFile);
				String delete= amazonClient.deleteFileFromS3Bucket(slideD.getImageUrl());
				String amazonUrl=amazonClient.uploadFile(multiparte);       
				slideD.setImageUrl(amazonUrl);
			}

			Slide slideUpdated = slideMapper.slideDtoToEntity(slideD);
			slideUpdated.setId(slideD.getId());
			slideRepository.save(slideUpdated);
			SlideGetDTO slideDto = slideMapper.slideEntityDto(slideUpdated);
			return slideDto;

		} else{

			throw new Exception("Slide no existe ");
		}
		
    }

	@Override
	public List<SlidesGetDTO> getAll() throws Exception {
		List<Slide> slides = slideRepository.findAll();

		if(slides.isEmpty()){
			throw new Exception("Lista vacia ");
		}else{
			List<SlidesGetDTO> getSlides = new ArrayList<>();
			for(Slide entity : slides){
				getSlides.add(slideMapper.getSlides(entity,new SlidesGetDTO()));
			}
			return getSlides;

		}
		
		
		
	}
    
}

