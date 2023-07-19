package com.webflux.example.globant_challenge.dto.external.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SecondaryCCResponse {

    private Double priceUsd;
}
