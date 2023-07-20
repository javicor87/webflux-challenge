package com.webflux.example.globant_challenge.dto.internal.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PurchaseReportDataRequest {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String model;
    private String cryptoCurrency;
}
