package com.paypal.transaction_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private Long receiverId;

    @Column(nullable = false)
    @Positive(message = "Amount must be Positive")
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

    @Column(nullable = false)
    private String status;

    @PrePersist
    public void prePersist() {
        if (timeStamp == null)
            timeStamp = LocalDateTime.now();
        if (status == null)
            status = "PENDING";
    }
}
