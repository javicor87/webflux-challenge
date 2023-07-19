package com.webflux.example.globant_challenge.controller;

import com.webflux.example.globant_challenge.constant.CarBrandEnum;
import com.webflux.example.globant_challenge.dto.internal.request.QuotationRequest;
import com.webflux.example.globant_challenge.dto.internal.response.ModelCarInfoResponse;
import com.webflux.example.globant_challenge.dto.internal.response.QuotationResponse;
import com.webflux.example.globant_challenge.mapper.QuotationMapper;
import com.webflux.example.globant_challenge.service.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/quotation")
public class QuotationController {

    private final QuotationService quotationService;

    @GetMapping(value = {"/info-cars", "/info-cars/{brand}"})
    public List<ModelCarInfoResponse> getInfoCars(
            @PathVariable(name = "brand", required = false) Optional<String> brand) {
        return quotationService.getModelCars(brand);
    }

    @GetMapping("/brands")
    public List<CarBrandEnum> getModelCars() {
        return List.of(CarBrandEnum.values());
    }

    @PostMapping
    public ResponseEntity<QuotationResponse> createQuotation(@RequestBody QuotationRequest quotationRequest)
            throws URISyntaxException {
        return ResponseEntity.created(new URI("/quotation")).body(
                QuotationMapper.buildQuotationResponse(quotationService.saveQuotation(quotationRequest))
        );
    }

}
