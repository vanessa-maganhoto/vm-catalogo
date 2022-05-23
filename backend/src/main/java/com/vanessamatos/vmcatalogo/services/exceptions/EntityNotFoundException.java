package com.vanessamatos.vmcatalogo.services.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public EntityNotFoundException(String msg){
        super(msg);
    }

}
