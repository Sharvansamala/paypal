package com.paypal.transaction_service.controller;

import com.paypal.transaction_service.dto.TransferRequest;
import com.paypal.transaction_service.dto.TransferResponse;
import com.paypal.transaction_service.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/create")
    public ResponseEntity<TransferResponse> createTransaction(@RequestBody TransferRequest transferRequest){
        TransferResponse transferResponse = transactionService.createTransaction(transferRequest);
        return new ResponseEntity<>(transferResponse, HttpStatus.CREATED);
    }
}
