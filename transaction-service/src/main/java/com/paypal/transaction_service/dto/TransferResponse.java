package com.paypal.transaction_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferResponse {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;
    private LocalDateTime timeStamp;
    private String status;
}
