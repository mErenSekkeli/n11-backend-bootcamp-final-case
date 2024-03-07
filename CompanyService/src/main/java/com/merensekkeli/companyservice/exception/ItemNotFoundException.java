package com.merensekkeli.companyservice.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message){
        super(message);
    }
}
