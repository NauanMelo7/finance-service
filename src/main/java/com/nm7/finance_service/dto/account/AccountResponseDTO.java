package com.nm7.finance_service.dto.account;

import java.math.BigDecimal;

public record AccountResponseDTO(
        String name,
        BigDecimal initialBalance,
        boolean active
) {
}
