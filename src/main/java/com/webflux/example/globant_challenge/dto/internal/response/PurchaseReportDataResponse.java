package com.webflux.example.globant_challenge.dto.internal.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PurchaseReportDataResponse {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String model;
    private String cryptoCurrency;
    private Double usdAmount;
    private Double cryptoCurrencyAmount;
}
