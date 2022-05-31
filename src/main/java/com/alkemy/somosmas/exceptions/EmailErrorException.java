package com.alkemy.somosmas.exceptions;

public class EmailErrorException extends Exception{

    public EmailErrorException(String message){
                super(message);
    }

    public EmailErrorException(){
        super();
    }
}
