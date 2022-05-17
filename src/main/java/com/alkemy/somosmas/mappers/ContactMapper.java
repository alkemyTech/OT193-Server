package com.alkemy.somosmas.mappers;

import com.alkemy.somosmas.dtos.ContactBasicDTO;
import com.alkemy.somosmas.dtos.ContactDTO;
import com.alkemy.somosmas.models.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public ContactBasicDTO original2Dto(ContactDTO contact){
        ContactBasicDTO dtoContact = new ContactBasicDTO();
        dtoContact.setEmail(contact.getEmail());
        dtoContact.setMessage(contact.getMessage());
        return dtoContact;
    }
}
