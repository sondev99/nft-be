package com.code.ecommerce.service;


import com.code.ecommerce.entity.User;
import com.code.ecommerce.entity.VerificationToken;

public interface VerificationService {
    VerificationToken saveUserVerificationToken(User theUser);
    //    AgoraResponseDto isValidVerificationToken(String token, String userId);
    //
    //    AgoraResponseDto resendActiveRegistration(String userId);
}
