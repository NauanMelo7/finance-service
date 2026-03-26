package com.nm7.finance_service.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        HttpStatus status,
        String error,
        String message,
        LocalDateTime timestamp
) {
}
