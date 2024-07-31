package com.code.ecommerce.repository;

import com.code.ecommerce.entity.VerificationToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
    Optional<VerificationToken> findByTokenAndUserId(String token, String userId);
    Optional<VerificationToken> findByToken(String token);
}
