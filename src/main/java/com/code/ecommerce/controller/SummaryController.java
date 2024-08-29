package com.code.ecommerce.controller;

import com.code.ecommerce.dto.response.ResponseMessage;
import com.code.ecommerce.dto.response.SummaryResponse;
import com.code.ecommerce.dto.response.VolumeDay;
import com.code.ecommerce.service.SummaryService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/summary")
@RestController
@Slf4j
@RequiredArgsConstructor
public class SummaryController {
    private final SummaryService summaryService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> summary(@RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                   @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseMessage.<SummaryResponse>builder().code(200).message("Create summary successful !!!").data(summaryService.getSummary(startDate, endDate)).build());


    }


    @GetMapping("/trading-volume-7day")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage> getTradingVolume7Day() {


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseMessage.<List<VolumeDay>>builder().code(200).message("Create summary successful !!!").data(summaryService.getTradingVolume7Day()).build());


    }

    @GetMapping("/chart-price-5days/{nftId}")
    public ResponseEntity<ResponseMessage> getChartPrice(@PathVariable  Long nftId ) {


        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseMessage.<List<VolumeDay>>builder().code(200).message("get chart 3 day successful !!!").data(summaryService.getChartPrice(nftId)).build());


    }
}

