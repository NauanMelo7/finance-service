package com.nm7.finance_service.dto.account;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AccountCreateDTO(
        @NotBlank
        @Size(min = 3, max = 100)
        String name,

        @Digits(integer = 19, fraction = 2)
        BigDecimal initialBalance) {

}
