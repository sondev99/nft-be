package com.code.ecommerce.service.impl;


import com.code.ecommerce.entity.User;
import com.code.ecommerce.entity.VerificationToken;
import com.code.ecommerce.repository.VerificationTokenRepository;
import com.code.ecommerce.service.VerificationService;
import java.util.Calendar;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final VerificationTokenRepository tokenRepository;

    @Override
    @Transactional
    public VerificationToken saveUserVerificationToken(User theUser) {
        var verificationToken = new VerificationToken();
        verificationToken.setUserId(theUser.getId());
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setCreatedDate(Calendar.getInstance().getTime());

        return tokenRepository.save(verificationToken);
    }

}
