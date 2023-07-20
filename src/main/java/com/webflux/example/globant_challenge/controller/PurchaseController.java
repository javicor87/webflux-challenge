package com.webflux.example.globant_challenge.controller;

import com.webflux.example.globant_challenge.constant.CryptoCurrencyEnum;
import com.webflux.example.globant_challenge.dto.internal.request.PurchaseReportRequest;
import com.webflux.example.globant_challenge.dto.internal.request.PurchaseRequest;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseReportResponse;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseResponse;
import com.webflux.example.globant_challenge.mapper.PurchaseMapper;
import com.webflux.example.globant_challenge.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponse> createPurchase(@RequestBody PurchaseRequest purchaseRequest)
            throws URISyntaxException {
        try {
            CryptoCurrencyEnum.valueOf(purchaseRequest.getData().getCryptoCurrency());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cryptocurrency not allowed");
        }
        return ResponseEntity.created(new URI("/purchase")).body(
                PurchaseMapper.buildPurchaseResponse(purchaseService.savePurchase(purchaseRequest))
        );
    }

    @PostMapping("/report")
    public ResponseEntity<PurchaseReportResponse> getPurchaseReport(
            @RequestBody PurchaseReportRequest purchaseReportRequest
    ) throws URISyntaxException {
        try {
            CryptoCurrencyEnum.valueOf(purchaseReportRequest.getData().getCryptoCurrency());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cryptocurrency not allowed");
        }
        return ResponseEntity.ok().body(
                PurchaseMapper.buildPurchaseReportResponse(purchaseService.getPurchaseReport(purchaseReportRequest))
        );
    }

}
