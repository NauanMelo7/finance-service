package com.nm7.finance_service.dto.account;

import com.nm7.finance_service.domain.account.StatusAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountResponseDTO(
        UUID id,
        String name,
        BigDecimal initialBalance,
        StatusAccount active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
