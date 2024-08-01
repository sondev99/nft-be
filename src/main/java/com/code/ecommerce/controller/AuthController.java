package com.code.ecommerce.controller;


import com.code.ecommerce.constance.ResponseStatus;
import com.code.ecommerce.dto.request.LoginRequest;
import com.code.ecommerce.dto.request.RegisterRequest;
import com.code.ecommerce.dto.response.AuthDto;
import com.code.ecommerce.dto.response.ResponseMessage;
import com.code.ecommerce.service.AuthService;
import com.code.ecommerce.service.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MailService mailService;

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("*** UserDto List, controller; login *");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseMessage.<AuthDto>builder().code(200).message("Login successfully !!!").data(authService.login(loginRequest)).build());
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody @Validated RegisterRequest registerRequest) {
        log.info("*** UserDto List, controller; register *");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseMessage.<String>builder().code(200).message("Register successful !!!").data(authService.register(registerRequest)).build());
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authService.refreshToken(request, response);
    }

//    @GetMapping("/send-mail")
//    public String sendMail(@RequestParam(name = "objectName") String objectName) {
//        mailService.sendVerificationEmail(objectName);
//        return "Email sent successfully";
//    }
}
