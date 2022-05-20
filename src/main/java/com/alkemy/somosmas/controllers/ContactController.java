package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.ContactBasicDTO;
import com.alkemy.somosmas.dtos.ContactDTO;
import com.alkemy.somosmas.models.Contact;
import com.alkemy.somosmas.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(value = "/contacts")
    public ResponseEntity<ContactBasicDTO> addContact(@RequestBody @Valid ContactDTO contact){
        ContactBasicDTO response = contactService.registerContact(contact);
        if(response!=null){
            return new ResponseEntity<ContactBasicDTO>(response, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<ContactBasicDTO>(response, HttpStatus.NO_CONTENT);
        }

    }
}
