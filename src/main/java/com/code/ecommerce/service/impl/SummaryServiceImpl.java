package com.code.ecommerce.service.impl;

import com.code.ecommerce.dto.response.SummaryResponse;
import com.code.ecommerce.dto.response.VolumeDay;
import com.code.ecommerce.repository.TransactionRepository;
import com.code.ecommerce.repository.UserRepository;
import com.code.ecommerce.service.SummaryService;
import com.code.ecommerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @Override
    public SummaryResponse getSummary(LocalDateTime startDate, LocalDateTime endDate) {
        return SummaryResponse.builder()
                .totalUser((startDate == null && endDate == null) ? userRepository.count() :userRepository.countUser(startDate, endDate))
                .totalTransaction((startDate == null && endDate == null) ? transactionRepository.count() : transactionRepository.countTransactionsBetweenDates(startDate, endDate))
                .totalTradingVolume(transactionRepository.findTotalPrice())
                .totalNft(transactionRepository.countNft())
                .build();
    }

    @Override
    public List<VolumeDay> getTradingVolume7Day() {

        LocalDate today = LocalDate.now();
        DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE");


        List<VolumeDay> volumeDays = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            String dayOfWeek = date.format(dayOfWeekFormatter);

            volumeDays.add(
                    VolumeDay.builder()
                            .volume(transactionService.getTransactionPriceByDay(date))
                            .dayOfWeek(dayOfWeek)
                            .build()
            );
        }


        return volumeDays;
    }
//    private final
}
