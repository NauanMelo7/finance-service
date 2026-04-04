package com.nm7.finance_service.service;

import com.nm7.finance_service.domain.account.Account;
import com.nm7.finance_service.domain.account.StatusAccount;
import com.nm7.finance_service.domain.category.Category;
import com.nm7.finance_service.domain.category.StatusCategory;
import com.nm7.finance_service.domain.transaction.Transaction;
import com.nm7.finance_service.domain.transaction.TransactionStatus;
import com.nm7.finance_service.dto.Transaction.CreateTransactionDTO;
import com.nm7.finance_service.dto.Transaction.TransactionResponseDTO;
import com.nm7.finance_service.dto.Transaction.UpdadeTransactionDTO;
import com.nm7.finance_service.exception.BusinessException;
import com.nm7.finance_service.repository.AccountRepository;
import com.nm7.finance_service.repository.CategoryRepository;
import com.nm7.finance_service.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository , AccountRepository accountRepository, CategoryRepository categoryRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    public TransactionResponseDTO createTransaction(CreateTransactionDTO body) {

        Account findAccount = this.accountRepository.findById(body.accountId())
                .orElseThrow(() -> new BusinessException("Account is not found by this ID", HttpStatus.NOT_FOUND));

        if(findAccount.getStatus() == StatusAccount.INACTIVE){
            throw new BusinessException("This account is inactive", HttpStatus.CONFLICT);
        }

        Category findCategory = this.categoryRepository.findById(body.categoryId())
                .orElseThrow(() -> new BusinessException("Category is not found by this ID", HttpStatus.NOT_FOUND));

        if(findCategory.getStatus() == StatusCategory.INACTIVE){
            throw new BusinessException("This category is inactive", HttpStatus.CONFLICT);
        }

        if(!body.type().name().equals(findCategory.getType().name())){
            throw new BusinessException("Transaction Type is incompatible witch category type", HttpStatus.CONFLICT);
        }

        Transaction createTransaction = new Transaction(
                body.type(),
                body.status(),
                body.amount(),
                body.ocurrenceDate(),
                body.description(),
                findAccount,
                findCategory
        );

        this.transactionRepository.save(createTransaction);

        return responseDTO(createTransaction);
    }

    public TransactionResponseDTO paidTransaction(UUID id) {
        Transaction findTransaction = this.transactionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Transaction not found", HttpStatus.NOT_FOUND));

        if(findTransaction.getStatus().equals(TransactionStatus.PAID)){
            throw new BusinessException("This transaction is already marked as paid", HttpStatus.CONFLICT);
        }

        findTransaction.setStatus(TransactionStatus.PAID);

        this.transactionRepository.save(findTransaction);

        return responseDTO(findTransaction);
    }

    public List<TransactionResponseDTO> findAllTransaction() {
        List<Transaction> findAll = this.transactionRepository.findAll();
        List<TransactionResponseDTO> responseTransaction = new ArrayList<>();

        for(Transaction findTransaction: findAll) {
            TransactionResponseDTO parseTransaction = new TransactionResponseDTO(
                    findTransaction.getId(),
                    findTransaction.getType(),
                    findTransaction.getStatus(),
                    findTransaction.getAmount(),
                    findTransaction.getOccurrenceDate(),
                    findTransaction.getDescription(),
                    findTransaction.getAccount().getId(),
                    findTransaction.getCategory().getId(),
                    findTransaction.getCreatedAt()
            );

            responseTransaction.add(parseTransaction);
        }

        return responseTransaction;
    }

    public TransactionResponseDTO findTransactionById(UUID id){
        Transaction findTransaction = this.transactionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Transaction is not found", HttpStatus.NOT_FOUND));

        return responseDTO(findTransaction);
    }

    public TransactionResponseDTO updateTransaction(UUID id, UpdadeTransactionDTO body){

        Transaction findTransaction = this.transactionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Transaction is not found", HttpStatus.NOT_FOUND));

        Account findAccount = this.accountRepository.findById(body.accountId())
                        .orElseThrow(() -> new BusinessException("Account is not found", HttpStatus.NOT_FOUND));

        Category findCategory = this.categoryRepository.findById(body.categoryId())
                        .orElseThrow(() -> new BusinessException("Category is not found", HttpStatus.NOT_FOUND));

        if(findTransaction.getStatus().equals(TransactionStatus.PAID)) {
            throw new BusinessException("This transaction with marked as paid, so it cannot be changed", HttpStatus.CONFLICT);
        }

        findTransaction.setType(body.type());
        findTransaction.setAmount(body.amount());
        findTransaction.setOccurrenceDate(body.occurrenceDate());
        findTransaction.setDescription(body.description());
        findTransaction.setAccount(findAccount);
        findTransaction.setCategory(findCategory);

        Transaction t = this.transactionRepository.save(findTransaction);

        return responseDTO(t);
    }

    public TransactionResponseDTO responseDTO(Transaction t) {
        return new TransactionResponseDTO(
                t.getId(),
                t.getType(),
                t.getStatus(),
                t.getAmount(),
                t.getOccurrenceDate(),
                t.getDescription(),
                t.getAccount().getId(),
                t.getCategory().getId(),
                t.getCreatedAt()
        );
    }
}
