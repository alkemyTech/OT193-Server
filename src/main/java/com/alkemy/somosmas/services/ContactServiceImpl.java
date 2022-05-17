package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.ContactBasicDTO;
import com.alkemy.somosmas.dtos.ContactDTO;
import com.alkemy.somosmas.mappers.ContactMapper;
import com.alkemy.somosmas.models.Contact;
import com.alkemy.somosmas.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;


    @Override
    public ContactBasicDTO registerContact(ContactDTO contactDto) throws Exception {
        if((contactDto.getName()!=null && contactDto.getEmail()!=null) && (contactDto.getName().length()>0 && contactDto.getEmail().length()>0)){
            Contact contactNew = new Contact();
            contactNew.setName(contactDto.getName());
            contactNew.setPhone(contactDto.getPhone());
            contactNew.setEmail(contactDto.getEmail());
            contactNew.setMessage(contactDto.getMessage());
            this.contactRepository.save(contactNew);
            ContactBasicDTO response = contactMapper.original2Dto(contactDto);
            return response;
        }else{
            throw new Exception("Uno de estos dos campos esta vacio 'Name' o 'Email'");
        }

    }
}
