package de.kausimausi.searchbackend.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.awt.font.ShapeGraphicAttribute;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchbackendException extends RuntimeException {
    private HttpStatus httpStatus;
    private ErrorMessage errorMessage;
    public SearchbackendException(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.errorMessage = new ErrorMessage(message);
    }
}
