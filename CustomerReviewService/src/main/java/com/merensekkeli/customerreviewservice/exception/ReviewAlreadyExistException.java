package com.merensekkeli.customerreviewservice.exception;

public class ReviewAlreadyExistException extends RuntimeException{
    public ReviewAlreadyExistException(String message) {
        super(message);
    }
}
