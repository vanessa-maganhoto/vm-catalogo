package com.vanessamatos.vmcatalogo.services.exceptions;

public class DataBaseException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public DataBaseException(String msg){
        super(msg);
    }

}
