package com.nm7.finance_service.dto.categories;

import jakarta.validation.constraints.NotBlank;

public record CategoriesCreateDTO(
        @NotBlank
        String name,
        @NotBlank
        String type
) {
}
