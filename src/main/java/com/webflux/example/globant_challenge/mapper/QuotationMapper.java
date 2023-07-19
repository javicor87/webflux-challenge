package com.webflux.example.globant_challenge.mapper;

import com.webflux.example.globant_challenge.domain.Quotation;
import com.webflux.example.globant_challenge.dto.internal.response.QuotationResponse;
import com.webflux.example.globant_challenge.dto.internal.response.VersionResponse;

import java.util.stream.Collectors;

public class QuotationMapper {

    public static QuotationResponse buildQuotationResponse(Quotation quotation) {
        return new QuotationResponse(
                quotation.getConvertionId(),
                quotation.getConvertionTimelife(),
                quotation.getVersions().stream().map(version -> new VersionResponse(
                        version.getModel(),
                        version.getVersion(),
                        version.getPriceUsd(),
                        version.getPriceCryptocurrency(),
                        version.getCryptoCurrency().name()
                )).collect(Collectors.toList())
        );
    }
}
