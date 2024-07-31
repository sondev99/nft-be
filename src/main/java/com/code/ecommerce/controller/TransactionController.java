package com.code.ecommerce.controller;

import com.code.ecommerce.dto.request.TransactionRequest;
import com.code.ecommerce.dto.response.ResponseMessage;
import com.code.ecommerce.dto.response.TransactionDto;
import com.code.ecommerce.dto.response.UserDto;
import com.code.ecommerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/transactions")
@RestController
@Slf4j
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ResponseMessage> saveTransaction(@RequestBody TransactionRequest transactionRequest, @PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseMessage.<TransactionDto>builder().code(200).message("Create transaction successful !!!").data(transactionService.saveTransaction(transactionRequest, id)).build());


    }
}
