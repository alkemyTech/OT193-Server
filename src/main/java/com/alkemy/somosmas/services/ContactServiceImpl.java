package com.alkemy.somosmas.services;

import com.alkemy.somosmas.dtos.ContactBasicDTO;
import com.alkemy.somosmas.dtos.ContactDTO;
import com.alkemy.somosmas.mappers.ContactMapper;
import com.alkemy.somosmas.models.Contact;
import com.alkemy.somosmas.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private EmailService emailService;

    @Override
    public ContactBasicDTO registerContact(ContactDTO contactDto) throws Exception {
            if(!this.contactRepository.existsByEmail(contactDto.getEmail())){
                Contact contactNew = new Contact();
                contactNew.setName(contactDto.getName());
                contactNew.setPhone(contactDto.getPhone());
                contactNew.setEmail(contactDto.getEmail());
                contactNew.setMessage(contactDto.getMessage());
                this.contactRepository.save(contactNew);
                ContactBasicDTO response = contactMapper.original2BasicDto(contactDto);
                emailService.sendWelcomeEmailTo(contactDto.getEmail(),"Estimado/a "+contactDto.getName()+
                        " Gracias por sumarte a somos mas", "Somos mas mail de bienvenida"  );
                return response;
            }else{
                throw new Exception("Mail existente");
            }

    }

    @Override
    public List<ContactDTO> getContactList() {
        List<Contact> model = contactRepository.findAll();
        return contactMapper.original2DtoList(model);
    }
}
