package com.webflux.example.globant_challenge.mapper;

import com.webflux.example.globant_challenge.domain.Purchase;
import com.webflux.example.globant_challenge.domain.Version;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseDataResponse;
import com.webflux.example.globant_challenge.dto.internal.response.PurchaseResponse;

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

}
