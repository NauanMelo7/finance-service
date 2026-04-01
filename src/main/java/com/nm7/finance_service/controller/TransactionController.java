package com.nm7.finance_service.controller;

import com.nm7.finance_service.dto.Transaction.CreateTransactionDTO;
import com.nm7.finance_service.dto.Transaction.TransactionResponseDTO;
import com.nm7.finance_service.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody CreateTransactionDTO body) {

        TransactionResponseDTO createTransaction = this.transactionService.createTransaction(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(createTransaction);

    }
}
