package com.nm7.finance_service.service;

import com.nm7.finance_service.domain.account.Account;
import com.nm7.finance_service.dto.account.AccountCreateDTO;
import com.nm7.finance_service.dto.account.AccountResponseDTO;
import com.nm7.finance_service.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountResponseDTO createAccount(AccountCreateDTO data){
        Account newAccount = new Account(
                data.name(),
                data.initialBalance()
        );

        Account savedAccount = accountRepository.save(newAccount);

        return new AccountResponseDTO(
                savedAccount.getName(),
                savedAccount.getInitialBalance(),
                savedAccount.isActive()
        );

    }
}
