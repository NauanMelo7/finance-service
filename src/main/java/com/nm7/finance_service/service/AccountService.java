package com.nm7.finance_service.service;

import com.nm7.finance_service.domain.account.Account;
import com.nm7.finance_service.dto.account.AccountCreateDTO;
import com.nm7.finance_service.dto.account.AccountResponseDTO;
import com.nm7.finance_service.dto.account.FindAccountResponse;
import com.nm7.finance_service.exception.BusinessException;
import com.nm7.finance_service.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountResponseDTO createAccount(AccountCreateDTO data){

        if(accountRepository.existsByName(data.name())) {
            throw new BusinessException("Account already exists", HttpStatus.CONFLICT);
        }

        Account newAccount = new Account(
                data.name(),
                data.initialBalance()
        );

        Account savedAccount = accountRepository.save(newAccount);


        return new AccountResponseDTO(
                savedAccount.getId(),
                savedAccount.getName(),
                savedAccount.getInitialBalance(),
                savedAccount.isActive(),
                savedAccount.getCreatedAt(),
                savedAccount.getUpdatedAt()
        );

    }

    public FindAccountResponse findAccount(UUID id) {
        Account findAccountById = accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Account is not found", HttpStatus.NOT_FOUND));

        return new FindAccountResponse(
                findAccountById.getId(),
                findAccountById.getName(),
                findAccountById.isActive(),
                findAccountById.getCreatedAt(),
                findAccountById.getUpdatedAt()
        );
    }

    public List<FindAccountResponse> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<FindAccountResponse> accountsResponses = new ArrayList<>();

        for(Account account :  accounts) {

            FindAccountResponse response = new FindAccountResponse(
                    account.getId(),
                    account.getName(),
                    account.isActive(),
                    account.getCreatedAt(),
                    account.getUpdatedAt()
            );


            accountsResponses.add(response);
        }

        return accountsResponses;

    }

    public void inactivateAccount(UUID id){
        Account findAccount = accountRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Account is not found", HttpStatus.NOT_FOUND));

        if(!findAccount.isActive()){
            throw new BusinessException("This account is inactive", HttpStatus.CONFLICT);
        }

        findAccount.setActive(false);

        accountRepository.save(findAccount);
    }
}
