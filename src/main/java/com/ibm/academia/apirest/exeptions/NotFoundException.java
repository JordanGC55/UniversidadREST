package com.ibm.academia.apirest.exeptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 7077841103628282982L;
}
