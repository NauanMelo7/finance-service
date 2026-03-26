package com.nm7.finance_service.dto.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponseDTO(
        String name,
        BigDecimal initialBalance,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
