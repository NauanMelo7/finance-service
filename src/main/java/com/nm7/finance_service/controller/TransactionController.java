package com.nm7.finance_service.controller;

import com.nm7.finance_service.dto.Transaction.CreateTransactionDTO;
import com.nm7.finance_service.dto.Transaction.TransactionResponseDTO;
import com.nm7.finance_service.dto.Transaction.UpdadeTransactionDTO;
import com.nm7.finance_service.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionResponseDTO>> findAllTransaction() {
        List<TransactionResponseDTO> findTransaction = this.transactionService.findAllTransaction();

        return ResponseEntity.ok(findTransaction);
    }

    @PostMapping("/transaction/{id}/paid")
    public ResponseEntity<TransactionResponseDTO> paidTransaction(@PathVariable UUID id){
        TransactionResponseDTO paidTransaction = this.transactionService.paidTransaction(id);

        return ResponseEntity.ok(paidTransaction);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<TransactionResponseDTO> findTransactionById(@PathVariable UUID id){
        TransactionResponseDTO findTransaction = this.transactionService.findTransactionById(id);

        return ResponseEntity.ok(findTransaction);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable UUID id, @RequestBody UpdadeTransactionDTO body) {
        TransactionResponseDTO updateTransaction = this.transactionService.updateTransaction(id, body);

        return ResponseEntity.ok(updateTransaction);
    }
}
