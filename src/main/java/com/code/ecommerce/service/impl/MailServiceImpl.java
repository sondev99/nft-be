package com.code.ecommerce.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.code.ecommerce.dto.MailEvent;
import com.code.ecommerce.exceptions.APIException;
import com.code.ecommerce.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.io.UnsupportedEncodingException;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;

    @Value("${client.baseUrl}")
    private String clientUrl;


    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;



    @Override
    public void sendVerificationEmail(MailEvent mailEvent) {
        String subject = "Email Verification";
        String senderName = "SG Shop";

        MimeMessage message = mailSender.createMimeMessage();
        log.info("Got message <{}>", mailEvent.getUserInfo().getId());

        String url = String.format("%s/verification/%s", clientUrl, mailEvent.getVerificationToken());

        log.info("Got url <{}>", url);

        try {
            var messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(from, senderName);
            messageHelper.setTo(mailEvent.getUserInfo().getEmail());
            messageHelper.setSubject(subject);
            Map<String, Object> context = Map.of("username", mailEvent.getUserInfo().getFirstName() + mailEvent.getUserInfo().getLastName(), "verificationLink", url);

            String mailContent = templateEngine.process(
                "verification_template", new Context(Locale.getDefault(), context));
            messageHelper.setText(mailContent, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error(e.getMessage());
            throw new APIException(e.getMessage());
        }

        mailSender.send(message);
    }
}
