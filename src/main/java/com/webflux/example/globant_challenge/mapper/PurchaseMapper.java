package com.webflux.example.globant_challenge.mapper;

import com.webflux.example.globant_challenge.domain.Purchase;
import com.webflux.example.globant_challenge.domain.Version;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseDataResponse;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseReportDataResponse;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseReportResponse;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseMapper {

    public static PurchaseResponse buildPurchaseResponse(Purchase purchase) {
        Version version = purchase.getQuotation().getVersions().stream().filter(
                v -> v.getModel().equals(purchase.getQuotation().getModel())
        ).collect(Collectors.toList()).get(0);
        return new PurchaseResponse(
                        new PurchaseDataResponse(
                                purchase.getClientName(),
                                version.getVersion(),
                                purchase.getQuotation().getModel(),
                                purchase.getQuotation().getCryptoCurrency().name(),
                                version.getPriceUsd(),
                                version.getPriceCryptocurrency(),
                                purchase.getCreatedAt(),
                                purchase.getPurchaseId()
                        )
        );
    }

    public static PurchaseReportResponse buildPurchaseReportResponse(List<Purchase> purchases) {
        PurchaseReportResponse report = new PurchaseReportResponse();
        List<PurchaseReportDataResponse> dataReport = new ArrayList<>();
        purchases.forEach(
                p -> {
                    p.getQuotation().getVersions().forEach(
                            v -> {
                                dataReport.add(
                                        new PurchaseReportDataResponse(
                                                p.getCreatedAt().toLocalDate(),
                                                v.getVersion(),
                                                v.getCryptoCurrency().name(),
                                                v.getPriceUsd(),
                                                v.getPriceCryptocurrency()
                                        )
                                );
                            }
                    );
                }
        );
        report.setData(dataReport);
        return report;
    }

}
