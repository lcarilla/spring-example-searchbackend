package de.kausimausi.searchbackend.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends SearchbackendException {
    public BadRequestException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
