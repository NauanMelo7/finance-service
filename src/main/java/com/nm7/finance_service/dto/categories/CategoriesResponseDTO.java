package com.nm7.finance_service.dto.categories;

import com.nm7.finance_service.domain.category.StatusCategory;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoriesResponseDTO(
        UUID id,
        String name,
        String type,
        StatusCategory status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
