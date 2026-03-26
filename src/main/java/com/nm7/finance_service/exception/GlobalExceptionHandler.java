package com.nm7.finance_service.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getStatus(),
                "Business rule violation",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

}
