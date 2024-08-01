package com.code.ecommerce.service;


import com.code.ecommerce.dto.request.TransactionRequest;
import com.code.ecommerce.dto.response.TransactionDto;
import com.code.ecommerce.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    TransactionDto saveTransaction(TransactionRequest transactionRequest, String id);

    List<TransactionDto> getAllTransaction();

    TransactionDto getTransactionById(String id);

    BigDecimal getTransactionPriceByDay(LocalDate day);

    TransactionDto createTransaction(TransactionRequest transactionRequest, String token);
}
