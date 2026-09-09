package com.tartaro.demo.resources.errors;
import com.tartaro.demo.services.middlewares.ResourceNotFoundException;
import com.tartaro.demo.services.middlewares.DataBaseException;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        String error = "Resource not found: ";
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandartError standartError = new StandartError(Instant.now(), httpStatus.value(), error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standartError);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandartError> database(DataBaseException ex, HttpServletRequest request) {
        String error = "Database error: ";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        StandartError standartError = new StandartError(Instant.now(), httpStatus.value(), error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(standartError);
    }


}
