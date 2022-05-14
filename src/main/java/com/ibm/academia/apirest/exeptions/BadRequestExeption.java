package com.ibm.academia.apirest.exeptions;

public class BadRequestExeption extends RuntimeException {

    public BadRequestExeption(String message) {
        super(message);
    }

    private static final long serialVersionUID = -5934285525213017689L;
}
