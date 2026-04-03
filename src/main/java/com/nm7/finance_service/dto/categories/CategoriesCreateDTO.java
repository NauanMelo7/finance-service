package com.nm7.finance_service.dto.categories;

import com.nm7.finance_service.domain.category.TypeCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriesCreateDTO(
        @NotBlank
        String name,
        @NotNull(message = "Type is required")
        TypeCategory type
) {
}
