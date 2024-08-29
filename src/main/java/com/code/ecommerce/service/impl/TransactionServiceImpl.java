package com.code.ecommerce.service.impl;

import com.code.ecommerce.dto.request.TransactionRequest;
import com.code.ecommerce.dto.response.TransactionDto;
import com.code.ecommerce.entity.Transaction;
import com.code.ecommerce.exceptions.NotFoundException;
import com.code.ecommerce.mapper.TransactionMapper;
import com.code.ecommerce.repository.TransactionRepository;
import com.code.ecommerce.repository.UserRepository;
import com.code.ecommerce.service.TransactionService;
import com.code.ecommerce.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
      transaction.setTransactionDate(LocalDate.now());
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
    List<Transaction> all = transactionRepository.findAll();
    List<TransactionDto> transactionDtos = new ArrayList<>();
    for (Transaction transaction : all) {
      TransactionDto transactionDto = transactionMapper.toDto(transaction);
      transactionDto.setUserId(transaction.getUser().getId());
      transactionDtos.add(transactionDto);
    }

    return transactionDtos;
  }

  @Override
  public TransactionDto getTransactionById(String id) {
    return transactionMapper.toDto(transactionRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Transaction not found")));
  }

  public BigDecimal getTransactionPriceByDay(LocalDate day) {
    List<Transaction> transactionByTransactionDate = transactionRepository.findTransactionByTransactionDate(
        day);
    return transactionByTransactionDate.stream()
        .map(Transaction::getPrice) // Lấy giá trị price từ mỗi transaction
        .reduce(BigDecimal.ZERO, BigDecimal::add); // Tính tổng các giá trị price


  }

  @Override
  public TransactionDto createTransaction(TransactionRequest transactionRequest, String token) {
    Claims claims = JwtUtils.parseClaims(JwtUtils.getTokenFromBearer(token));
    String userId = (String) claims.get("userId");
    Transaction transaction = transactionMapper.toTransaction(transactionRequest);
    transaction.setTransactionDate(LocalDate.now());
    transaction.setUser(
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found")));

    return transactionMapper.toDto(transactionRepository.save(transaction));
  }

  @Override
  public BigDecimal getChartByDay(LocalDate date, Long nftId) {
    List<Transaction> transactionByTransactionDate = transactionRepository.findTransactionByTransactionDateAndNftId(
        date, nftId);
// Kiểm tra nếu không có giao dịch nào
    if (transactionByTransactionDate.isEmpty()) {
      return BigDecimal.ZERO; // Hoặc bất kỳ giá trị mặc định nào bạn muốn trả về khi không có giao dịch
    }

    // Tính tổng giá trị price
    BigDecimal totalPrice = transactionByTransactionDate.stream()
        .map(Transaction::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    // Tính số lượng giao dịch
    long count = transactionByTransactionDate.size();

    // Tính trung bình
    return totalPrice.divide(BigDecimal.valueOf(count), RoundingMode.HALF_UP);
  }
}
