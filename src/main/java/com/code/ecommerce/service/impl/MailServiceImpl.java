package com.code.ecommerce.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.code.ecommerce.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;

//    @Value("${client.baseUrl}")
//    private String clientUrl;

//    @Value("${cloud.aws.bucket.name}")
//    private String bucketName;
//
//    @Value("${cloud.aws.region.static}")
//    private String region;
//
//    @Value("${cloud.aws.path}")
//    private String path;


    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

//    private final AmazonS3 s3Client;


    @Override
    public void sendVerificationEmail(String objectName) {
        String subject = "Email Verification";
        String senderName = "SG Shop";

        MimeMessage message = mailSender.createMimeMessage();
//        log.info("Got message <{}>", mailEvent.getUserInfo().getId());

//        String url = String.format("%s/verification/%s", clientUrl, mailEvent.getVerificationToken());

//        log.info("Got url <{}>", url);
//        S3Object s3Object = s3Client.getObject("ncp-storage", "1714026981818_toyota-corola.jpg");
//        Resource resource = new InputStreamResource(s3Object.getObjectContent());


        try {
            var messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(from, senderName);
            messageHelper.setTo("sondev2205@gmail.com");
            messageHelper.setSubject(subject);
            messageHelper.setText("abcd");


//            messageHelper.addAttachment("Invoice", resource);
//            messageHelper.setSubject(subject);
//            Map<String, Object> context = Map.of("username", "Son" + "Tran", "verificationLink", "https://vticloud.io/amazon-ses-la-gi-huong-dan-tong-hop-ve-dich-vu-amazon-ses/");
//
//            String mailContent = templateEngine.process(
//                    "verification_template", new Context(Locale.getDefault(), context));
//            messageHelper.setText(mailContent, true);


        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        mailSender.send(message);
    }
}
