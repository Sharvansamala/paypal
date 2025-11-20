package com.paypal.transaction_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.transaction_service.Repository.TransactionRepository;
import com.paypal.transaction_service.dto.TransferRequest;
import com.paypal.transaction_service.dto.TransferResponse;
import com.paypal.transaction_service.entity.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ObjectMapper objectMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ObjectMapper objectMapper) {
        this.transactionRepository = transactionRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public TransferResponse createTransaction(TransferRequest transferRequest) {
        Transaction transaction = new Transaction();
        transaction.setSenderId(transferRequest.getSenderId());
        transaction.setReceiverId(transferRequest.getReceiverId());
        transaction.setAmount(transferRequest.getAmount());
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setStatus("SUCCESS");
        transaction = transactionRepository.save(transaction);
        return TransferResponse.builder()
                .id(transaction.getId())
                .senderId(transaction.getSenderId())
                .receiverId(transaction.getReceiverId())
                .amount(transaction.getAmount())
                .timeStamp(transaction.getTimeStamp())
                .status(transaction.getStatus())
                .build();
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return List.of();
    }
}
