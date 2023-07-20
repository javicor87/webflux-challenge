package com.webflux.example.globant_challenge.dto.internal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationRequest {

    private QuotationDataRequest data;
}
