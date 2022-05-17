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
    public Boolean saveContact(Contact newContact) throws Exception {
        String name = newContact.getName();
        String email = newContact.getEmail();
        if(name!=null && email!=null){
            this.contactRepository.save(newContact);
            return true;
        }else{
            throw new Exception("Uno de estos dos campos está vacío 'Name' y 'Email'");
        }
    }

    @Override
    public ContactBasicDTO registerContact(ContactDTO contactDto) {
        Contact contactNew = new Contact();
        contactNew.setName(contactDto.getName());
        contactNew.setPhone(contactDto.getPhone());
        contactNew.setEmail(contactDto.getEmail());
        contactNew.setMessage(contactDto.getMessage());
        this.contactRepository.save(contactNew);
        ContactBasicDTO response = contactMapper.original2Dto(contactDto);
        return response;
    }
}
