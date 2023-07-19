package com.webflux.example.globant_challenge.dto.internal.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuotationDataRequest {

    private String model;
    private String cryptoCurrency;
}
