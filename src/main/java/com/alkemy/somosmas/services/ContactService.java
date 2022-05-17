package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.ContactBasicDTO;
import com.alkemy.somosmas.dtos.ContactDTO;
import com.alkemy.somosmas.models.Contact;

public interface ContactService {
    Boolean saveContact(Contact newContact) throws Exception;
    ContactBasicDTO registerContact(ContactDTO contact);
}
