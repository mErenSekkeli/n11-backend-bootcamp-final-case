package com.merensekkeli.customerreviewservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReviewAlreadyExistException extends RuntimeException{
    public ReviewAlreadyExistException(String message) {
        super(message);
    }
}
