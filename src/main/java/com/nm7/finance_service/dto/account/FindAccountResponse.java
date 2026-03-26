package com.nm7.finance_service.dto.account;

import java.time.LocalDateTime;
import java.util.UUID;

public record FindAccountResponse(
        UUID id,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAd
) {
}
