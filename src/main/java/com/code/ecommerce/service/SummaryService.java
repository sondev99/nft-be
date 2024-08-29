package com.code.ecommerce.service;

import com.code.ecommerce.dto.response.SummaryResponse;
import com.code.ecommerce.dto.response.VolumeDay;

import java.time.LocalDateTime;
import java.util.List;

public interface SummaryService {
    SummaryResponse getSummary(LocalDateTime startDate, LocalDateTime endDate);

    List<VolumeDay> getTradingVolume7Day();

  List<VolumeDay> getChartPrice(Long nftId);
}
