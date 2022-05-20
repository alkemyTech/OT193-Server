package com.alkemy.somosmas.mappers;

import com.alkemy.somosmas.dtos.ContactBasicDTO;
import com.alkemy.somosmas.dtos.ContactDTO;
import com.alkemy.somosmas.models.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactMapper {

    public ContactBasicDTO original2BasicDto(ContactDTO contact){
        ContactBasicDTO dtoContact = new ContactBasicDTO();
        dtoContact.setEmail(contact.getEmail());
        dtoContact.setMessage(contact.getMessage());
        return dtoContact;
    }

    private ContactDTO original2Dto(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName(contact.getName());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setEmail(contact.getEmail());
        contactDTO.setMessage(contact.getMessage());
        return contactDTO;
    }

    public List<ContactDTO> original2DtoList(List<Contact> contactList) {
        List<ContactDTO> dtoList = contactList
                .stream()
                .map(i->this.original2Dto(i))
                .collect(Collectors.toList());
        return null;
    }


}
