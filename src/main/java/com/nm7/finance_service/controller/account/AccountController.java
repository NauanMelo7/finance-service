package com.nm7.finance_service.controller.account;

import com.nm7.finance_service.dto.account.AccountCreateDTO;
import com.nm7.finance_service.dto.account.AccountResponseDTO;
import com.nm7.finance_service.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class AccountController {

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountCreateDTO data) {

        AccountResponseDTO accountCreated = accountService.createAccount(data);

        return ResponseEntity.ok(accountCreated);
    }
}
