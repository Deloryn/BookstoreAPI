package com.example.bookstoreapi.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(Class resourceClass, Long resourceId) {
        super("The "
                + resourceClass.getName()
                + " with id: "
                + resourceId.toString()
                + " was not found.");
    }
}
