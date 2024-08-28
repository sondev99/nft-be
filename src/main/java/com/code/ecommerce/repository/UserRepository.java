package com.code.ecommerce.repository;


import com.code.ecommerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    Page<User> findByEmailContainingIgnoreCase(String searchText, Pageable pageable);


    @Query("SELECT COUNT(t) FROM User t WHERE t.createdAt BETWEEN :startDate AND :endDate")
    long countUser(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT t FROM User t WHERE t.walletAddress = :walletAddress")
    User findByWalletAddress(String walletAddress);
}