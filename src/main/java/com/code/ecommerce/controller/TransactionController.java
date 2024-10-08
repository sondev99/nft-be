package com.code.ecommerce.controller;

import com.code.ecommerce.dto.request.TransactionRequest;
import com.code.ecommerce.dto.response.ResponseMessage;
import com.code.ecommerce.dto.response.TransactionDto;
import com.code.ecommerce.entity.Transaction;
import com.code.ecommerce.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public ResponseEntity<ResponseMessage> createTransaction(@RequestBody TransactionRequest transactionRequest, @RequestHeader("Authorization") String token) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseMessage.<TransactionDto>builder().code(200).message("Create transaction successful !!!").data(transactionService.createTransaction(transactionRequest, token)).build());


    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> getAllTransaction() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseMessage.<List<TransactionDto>>builder().code(200).message("Get all transaction successfully !!!").data(transactionService.getAllTransaction()).build());


    }

}
