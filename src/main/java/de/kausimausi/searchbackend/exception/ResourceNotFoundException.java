package de.kausimausi.searchbackend.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends SearchbackendException {
    public ResourceNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
