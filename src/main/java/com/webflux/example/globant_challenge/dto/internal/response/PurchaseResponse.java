package com.webflux.example.globant_challenge.dto.internal.response;

import com.webflux.example.globant_challenge.dto.internal.request.PurchaseDataRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseResponse {

    private PurchaseDataResponse data;

}
