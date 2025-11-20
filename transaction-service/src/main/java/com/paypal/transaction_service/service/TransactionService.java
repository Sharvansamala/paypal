package com.paypal.transaction_service.service;

import com.paypal.transaction_service.dto.TransferRequest;
import com.paypal.transaction_service.dto.TransferResponse;
import com.paypal.transaction_service.entity.Transaction;

import java.util.List;

public interface TransactionService {

    TransferResponse createTransaction(TransferRequest transferRequest);

    List<Transaction> getAllTransaction();
}
