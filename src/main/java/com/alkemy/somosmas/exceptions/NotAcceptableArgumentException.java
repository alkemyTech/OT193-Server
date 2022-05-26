package com.alkemy.somosmas.exceptions;

public class NotAcceptableArgumentException extends Exception{



    public NotAcceptableArgumentException(String message){

        super(message);
    }

    public NotAcceptableArgumentException(){
        super();
    }


}