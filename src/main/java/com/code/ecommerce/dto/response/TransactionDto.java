package com.code.ecommerce.dto.response;

import com.code.ecommerce.entity.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TransactionDto {
    private String id;
    private Long nftId;
    private Integer quantity;
    private BigDecimal price;
    private LocalDate transactionDate;
    private String walletAddress;
    private String UserId;

}
