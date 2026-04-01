package com.nm7.finance_service.controller;

import com.nm7.finance_service.dto.account.AccountCreateDTO;
import com.nm7.finance_service.dto.account.AccountResponseDTO;
import com.nm7.finance_service.dto.account.FindAccountResponse;
import com.nm7.finance_service.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")

public class AccountController {

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountCreateDTO data) {

        AccountResponseDTO accountCreated = accountService.createAccount(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(accountCreated);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<FindAccountResponse> findAccountById(@PathVariable UUID id){
        FindAccountResponse findAccount = accountService.findAccount(id);

        return ResponseEntity.ok(findAccount);
    }

    @PatchMapping("/account/{id}/inactivate")
    public ResponseEntity<Void> inactivateAccount(@PathVariable UUID id){
        accountService.inactivateAccount(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/account")
    public ResponseEntity<List<FindAccountResponse>> getAllAccount() {
        List<FindAccountResponse> getAllAccount = accountService.findAllAccounts();



        return ResponseEntity.ok(getAllAccount);
    }
}
