package com.andreluiscardoso.digitalwallet.infrastructure.exception;

import com.andreluiscardoso.digitalwallet.util.HttpErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpErrorResponse> authenticationException(HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse(
                httpStatus.getReasonPhrase(),
                httpStatus.value(),
                "Incorrect email or password",
                request.getRequestURI(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(httpErrorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> entityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse(
                httpStatus.getReasonPhrase(),
                httpStatus.value(),
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(httpStatus).body(httpErrorResponse);
    }
}
