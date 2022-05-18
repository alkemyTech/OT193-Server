package com.alkemy.somosmas.services;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.alkemy.somosmas.dtos.SlideDTO;
import com.alkemy.somosmas.mappers.SlideMapper;
import com.alkemy.somosmas.models.Slide;
import com.alkemy.somosmas.repositories.SlideRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl implements SlideService{

    @Autowired
    SlideRepository slideRepository;

    @Autowired SlideMapper slideMapper;
    @Override
    public Slide create(SlideDTO slideDTO) {
        String imgUrl= slideDTO.getImageUrl();
        String encodeImgUrl= Base64.getEncoder().encodeToString(imgUrl.getBytes());
        slideDTO.setImageUrl(encodeImgUrl);

        if(slideDTO.getOrder_ong()==null){
          List<Slide> listSlide=  slideRepository.findAll();
          try {
            Slide ultSlide=listSlide.get(listSlide.size()-1);
            Integer ultOrder=  ultSlide.getOrder_ong();
                ultOrder++;
                slideDTO.setOrder_ong(ultOrder);
            
          } catch (IndexOutOfBoundsException e) {
              //TODO: handle exception
              System.out.println( "Error al obtener el  listado de Slides: "+ e );
              slideDTO.setOrder_ong(1);
          }
          
        }

        Slide slide = slideMapper.create(slideDTO); 
        return slideRepository.save(slide);
       // return null;
    }

    @Override
    public SlideDTO delete(SlideDTO slideDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SlideDTO> getSlides() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SlideDTO getSlide(Long id) {
        Optional<Slide> slide= slideRepository.findById(id);
        SlideDTO slideDTO= slideMapper.getSlide(slide.get());       
        return slideDTO;
    }
    
}
