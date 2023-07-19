package com.webflux.example.globant_challenge.dto.internal.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class QuotationResponse {
    private UUID convertionId;
    private int convertionTimelife;
    private List<VersionResponse> versionResponses;
}
