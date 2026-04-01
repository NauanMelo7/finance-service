package com.nm7.finance_service.dto.Transaction;

import com.nm7.finance_service.domain.transaction.TransactionType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;



public record CreateTransactionDTO(
        TransactionType transactionType,
        @Digits(integer = 19, fraction = 2)
        BigDecimal amount,
        LocalDateTime ocurrenceDate,
        @Size(min = 3, max = 100)
        String description,
        UUID accountId,
        UUID categoryId
) {
}

