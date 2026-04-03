package com.nm7.finance_service.dto.categories;

import com.nm7.finance_service.domain.category.StatusCategory;
import com.nm7.finance_service.domain.category.TypeCategory;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoriesResponseDTO(
        UUID id,
        String name,
        TypeCategory type,
        StatusCategory status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
