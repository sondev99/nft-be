package com.code.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;
@Data
@Builder
public class VolumeDay {
    private String dayOfWeek;
    private BigDecimal volume;
}
