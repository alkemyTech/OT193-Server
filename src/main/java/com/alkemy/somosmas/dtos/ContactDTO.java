package com.alkemy.somosmas.dtos;


import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ContactDTO {

    @NotBlank
    private String name;
    private Integer phone;
    @NotBlank
    private String email;
    private String message;

    private ContactDTO(String name, Integer phone, String email, String message){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.message = message;
    }

    public ContactDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
