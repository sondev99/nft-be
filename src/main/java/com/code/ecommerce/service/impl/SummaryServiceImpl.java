package com.code.ecommerce.service.impl;

import com.code.ecommerce.dto.response.SummaryResponse;
import com.code.ecommerce.mapper.UserMapper;
import com.code.ecommerce.repository.TransactionRepository;
import com.code.ecommerce.repository.UserRepository;
import com.code.ecommerce.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public SummaryResponse getSummary(LocalDateTime startDate, LocalDateTime endDate) {
        return SummaryResponse.builder()
                .totalUser((startDate == null && endDate == null) ? userRepository.count() :userRepository.countUser(startDate, endDate))
                .totalTransaction((startDate == null && endDate == null) ? transactionRepository.count() : transactionRepository.countTransactionsBetweenDates(startDate, endDate))
                .totalTradingVolume(transactionRepository.findTotalPrice())
                .build();
    }
//    private final
}
