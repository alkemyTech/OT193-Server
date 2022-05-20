package com.alkemy.somosmas.dtos;

public class ContactBasicDTO {
    private String email;
    private String message;

    public ContactBasicDTO(String email, String message){
        this.email = email;
        this.message = message;
    }
    public ContactBasicDTO(){

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
