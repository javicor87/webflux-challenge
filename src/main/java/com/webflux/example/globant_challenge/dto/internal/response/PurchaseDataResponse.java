package com.webflux.example.globant_challenge.dto.internal.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PurchaseDataResponse {

    private String fullName;
    private String version;
    private String model;
    private String cyrptoCurrency;
    private Double priceUsd;
    private Double priceCryptoCurrency;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private UUID purchaseId;

}
