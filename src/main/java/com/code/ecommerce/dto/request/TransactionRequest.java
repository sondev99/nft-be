package com.code.ecommerce.dto.request;

import com.code.ecommerce.entity.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class TransactionRequest {
    private Long nftId;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime transactionDate;
    private String walletAddress;
    private String userId;
}
