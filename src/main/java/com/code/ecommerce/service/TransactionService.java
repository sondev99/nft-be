package com.code.ecommerce.service;


import com.code.ecommerce.dto.request.TransactionRequest;
import com.code.ecommerce.dto.response.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto saveTransaction(TransactionRequest transactionRequest, String id);

    List<TransactionDto> getAllTransaction();

    TransactionDto getTransactionById(String id);

}
