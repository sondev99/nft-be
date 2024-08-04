package com.code.ecommerce.dto.request;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class TransactionRequest {
    private Long nftId;
    private Integer quantity;
    private BigDecimal price;
    private String walletAddress;
}
