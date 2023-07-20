package com.webflux.example.globant_challenge.controller;

import com.webflux.example.globant_challenge.dto.internal.request.PurchaseReportRequest;
import com.webflux.example.globant_challenge.dto.internal.request.PurchaseRequest;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseReportResponse;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseResponse;
import com.webflux.example.globant_challenge.mapper.PurchaseMapper;
import com.webflux.example.globant_challenge.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.created(new URI("/purchase")).body(
                PurchaseMapper.buildPurchaseResponse(purchaseService.savePurchase(purchaseRequest))
        );
    }

    @PostMapping("/report")
    public ResponseEntity<PurchaseReportResponse> getPurchaseReport(
            @RequestBody PurchaseReportRequest purchaseReportRequest
    ) throws URISyntaxException {
        return ResponseEntity.created(new URI("/report")).body(
                PurchaseMapper.buildPurchaseReportResponse(purchaseService.getPurchaseReport(purchaseReportRequest))
        );
    }

}
