package com.nm7.finance_service.repository;

import com.nm7.finance_service.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    boolean existsByName(String name);
}
