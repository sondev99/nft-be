package com.code.ecommerce.service;


import com.code.ecommerce.dto.MailEvent;

public interface MailService {

    void sendVerificationEmail(MailEvent mailEvent);

}
