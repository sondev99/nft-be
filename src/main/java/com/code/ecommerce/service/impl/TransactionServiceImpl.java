package com.code.ecommerce.service.impl;

import com.code.ecommerce.dto.request.TransactionRequest;
import com.code.ecommerce.dto.response.TransactionDto;
import com.code.ecommerce.entity.Transaction;
import com.code.ecommerce.exceptions.NotFoundException;
import com.code.ecommerce.mapper.TransactionMapper;
import com.code.ecommerce.repository.TransactionRepository;
import com.code.ecommerce.repository.UserRepository;
import com.code.ecommerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserRepository userRepository;

    @Override
    public TransactionDto saveTransaction(TransactionRequest transactionRequest, String id) {
        Transaction transactionEntity = transactionRepository.findById(id).map(transaction -> {
            transaction.setTransactionDate(transactionRequest.getTransactionDate());
//            transaction.setUser(userRepository.findById(transactionRequest.getUserId()).orElseThrow(() -> new NotFoundException("User not found")));
            transaction.setPrice(transactionRequest.getPrice());
            transaction.setQuantity(transactionRequest.getQuantity());
            return transactionRepository.save(transaction);
        }).orElseGet(
                () -> transactionRepository.save(transactionMapper.toTransaction(transactionRequest)));

        return transactionMapper.toDto(transactionEntity);
    }

    @Override
    public List<TransactionDto> getAllTransaction() {


        return transactionMapper.toDto(transactionRepository.findAll());
    }

    @Override
    public TransactionDto getTransactionById(String id) {
        return transactionMapper.toDto(transactionRepository.findById(id).orElseThrow(() -> new NotFoundException("Transaction not found")));
    }
}
