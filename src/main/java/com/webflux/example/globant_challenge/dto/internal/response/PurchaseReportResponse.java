package com.webflux.example.globant_challenge.dto.internal.response;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseReportResponse {

    private List<PurchaseReportDataResponse> data;
}
