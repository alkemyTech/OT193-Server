package com.alkemy.somosmas.controllers;

import com.alkemy.somosmas.dtos.ContactBasicDTO;
import com.alkemy.somosmas.dtos.ContactDTO;
import com.alkemy.somosmas.models.Contact;
import com.alkemy.somosmas.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/contacts")
    public List<ContactDTO> getContactList(){
        List<ContactDTO> dtoList = contactService.getContactList();
        return dtoList;
    }

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
