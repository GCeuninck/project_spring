package com.example.project_spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlbumExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(Exception exc){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        return new ResponseEntity<>(error, error.getStatus());
    }
}
