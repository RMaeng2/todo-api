package com.example.todo.exception;

import com.example.todo.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException e) {
        String code = e.getStatusCode().toString();
        String message = e.getReason() != null ? e.getReason() : "Error";
        return ResponseEntity.status(e.getStatusCode())
                .body(new ErrorResponse(code, message));
    }
}
