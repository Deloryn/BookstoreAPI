package com.example.bookstoreapi.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {
    private List<String> details;
    public InvalidInputException(List<FieldError> errors) {
        super("The provided data is not valid!");
        generateDetails(errors);
    }

    private void generateDetails(List<FieldError> errors) {
        details = new ArrayList<>();
        for(FieldError e : errors) {
            details.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
        }
    }

    public List<String> getDetails() {
        return this.details;
    }
}
