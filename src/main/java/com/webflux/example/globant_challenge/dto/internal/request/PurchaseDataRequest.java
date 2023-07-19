package com.webflux.example.globant_challenge.dto.internal.request;

import lombok.Data;

import java.util.UUID;

@Data
public class PurchaseDataRequest {

    private UUID convertionId;
    private String fullName;
    private String version;
    private String model;
    private String cryptoCurrency;

}
