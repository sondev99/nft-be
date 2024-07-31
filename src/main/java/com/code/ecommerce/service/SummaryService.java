package com.code.ecommerce.service;

import com.code.ecommerce.dto.response.SummaryResponse;

import java.time.LocalDateTime;

public interface SummaryService {
    SummaryResponse getSummary(LocalDateTime startDate, LocalDateTime endDate);
}
