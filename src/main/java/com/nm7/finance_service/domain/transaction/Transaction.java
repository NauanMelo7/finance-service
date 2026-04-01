package com.nm7.finance_service.domain.transaction;
import com.nm7.finance_service.domain.account.Account;
import com.nm7.finance_service.domain.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "transaction")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "type")
    private TransactionType transactionType;

    @Column(name = "amount")
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "occurrence_date", nullable = false, updatable = false)
    private LocalDateTime occurrenceDate;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public Transaction(TransactionType transactionType,BigDecimal amount,LocalDateTime ocurrenceDate, String description, Account account, Category category){
        this.transactionType = transactionType;
        this.amount = amount;
        this.occurrenceDate = ocurrenceDate;
        this.description = description;
        this.account = account;
        this.category = category;
    }

}
