package com.nm7.finance_service.dto.account;

import java.math.BigDecimal;

public record AccountCreateDTO(String name, BigDecimal initialBalance) {

}
