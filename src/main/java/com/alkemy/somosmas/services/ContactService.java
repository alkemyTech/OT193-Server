package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.ContactBasicDTO;
import com.alkemy.somosmas.dtos.ContactDTO;
import com.alkemy.somosmas.models.Contact;

import java.util.List;

public interface ContactService {
    ContactBasicDTO registerContact(ContactDTO contact) throws Exception;
    List<ContactDTO> getContactList();
}
