package com.alkemy.somosmas.exceptions;

public class PageEmptyException extends Exception{


    private final static String PAGE_DOESNT_EXIST = "page %s in model %s does not exist";
    public PageEmptyException(int id, String modelName){
        super(String.format(PAGE_DOESNT_EXIST,id,modelName));
    }

    public PageEmptyException(){
        super();
    }


}
