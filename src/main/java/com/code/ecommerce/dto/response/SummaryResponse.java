package com.code.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SummaryResponse {

    private BigDecimal totalTradingVolume;
    private Long totalUser;
    private Long totalTransaction;
    private Long totalNft;
}
