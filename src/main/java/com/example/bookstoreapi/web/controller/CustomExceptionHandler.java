package com.example.bookstoreapi.web.controller;

import com.example.bookstoreapi.web.error.ErrorResponse;
import com.example.bookstoreapi.web.error.InvalidInputException;
import com.example.bookstoreapi.web.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Resource Not Found", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.addAll(ex.getDetails());
        ErrorResponse error = new ErrorResponse("Invalid Input", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
