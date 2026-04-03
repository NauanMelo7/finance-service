package com.nm7.finance_service.dto.Transaction;

import com.nm7.finance_service.domain.transaction.TransactionStatus;
import com.nm7.finance_service.domain.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponseDTO(
        UUID id,
        TransactionType type,
        TransactionStatus status,
        BigDecimal amount,
        LocalDateTime occurrenceDate,
        String description,
        UUID accountId,
        UUID categoryId,
        LocalDateTime createdAt
) {
}
