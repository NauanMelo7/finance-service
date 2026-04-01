package com.nm7.finance_service.dto.account;

import com.nm7.finance_service.domain.account.StatusAccount;

import java.time.LocalDateTime;
import java.util.UUID;

public record FindAccountResponse(
        UUID id,
        String name,
        StatusAccount active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
