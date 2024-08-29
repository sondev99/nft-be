package com.code.ecommerce.repository;

import com.code.ecommerce.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("SELECT SUM(t.price) FROM Transaction t WHERE t.transactionDate BETWEEN :startDate AND :endDate")
    BigDecimal findTotalPriceBetween();

    @Query("SELECT SUM(t.price) FROM Transaction t")
    BigDecimal findTotalPrice();

    @Query("SELECT SUM(t.quantity) FROM Transaction t")
    Long countNft();


    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.transactionDate BETWEEN :startDate AND :endDate")
    long countTransactionsBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Transaction> findTransactionByTransactionDate(LocalDate transactionDate);

    List<Transaction> findTransactionByTransactionDateAndNftId(LocalDate date, Long nftId);
}
