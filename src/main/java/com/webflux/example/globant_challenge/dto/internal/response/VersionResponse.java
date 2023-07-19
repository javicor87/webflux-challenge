package com.webflux.example.globant_challenge.dto.internal.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VersionResponse {

    private String model;
    private String version;
    private Double priceUsd;
    private Double priceCryptocurrency;
    private String cryptocurrency;
}
