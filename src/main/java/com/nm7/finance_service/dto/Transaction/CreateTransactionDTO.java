package com.nm7.finance_service.dto.Transaction;

import com.nm7.finance_service.domain.transaction.TransactionStatus;
import com.nm7.finance_service.domain.transaction.TransactionType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;



public record CreateTransactionDTO(
        @NotNull
        TransactionType type,
        @NotNull
        TransactionStatus status,
        @Digits(integer = 19, fraction = 2)
        @PositiveOrZero
        BigDecimal amount,
        @NotNull(message = "Occurrence date is required")
        LocalDateTime ocurrenceDate,
        @Size(min = 3, max = 100)
        String description,
        UUID accountId,
        UUID categoryId
) {
}

