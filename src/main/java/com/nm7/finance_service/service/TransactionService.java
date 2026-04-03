package com.nm7.finance_service.service;

import com.nm7.finance_service.domain.account.Account;
import com.nm7.finance_service.domain.account.StatusAccount;
import com.nm7.finance_service.domain.category.Category;
import com.nm7.finance_service.domain.category.StatusCategory;
import com.nm7.finance_service.domain.transaction.Transaction;
import com.nm7.finance_service.domain.transaction.TransactionType;
import com.nm7.finance_service.dto.Transaction.CreateTransactionDTO;
import com.nm7.finance_service.dto.Transaction.TransactionResponseDTO;
import com.nm7.finance_service.exception.BusinessException;
import com.nm7.finance_service.repository.AccountRepository;
import com.nm7.finance_service.repository.CategoryRepository;
import com.nm7.finance_service.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

        return new TransactionResponseDTO(
                createTransaction.getId(),
                createTransaction.getType(),
                createTransaction.getStatus(),
                createTransaction.getAmount(),
                createTransaction.getOccurrenceDate(),
                createTransaction.getDescription(),
                createTransaction.getAccount().getId(),
                createTransaction.getCategory().getId(),
                createTransaction.getCreatedAt()
        );
    }
}
