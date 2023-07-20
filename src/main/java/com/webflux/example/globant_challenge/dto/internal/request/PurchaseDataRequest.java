package com.webflux.example.globant_challenge.dto.internal.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PurchaseDataRequest {

    private UUID convertionId;
    private String fullName;
    private String version;
    private String model;
    private String cryptoCurrency;

}
