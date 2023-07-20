package com.webflux.example.globant_challenge;

import com.webflux.example.globant_challenge.dto.internal.request.QuotationDataRequest;
import com.webflux.example.globant_challenge.dto.internal.request.QuotationRequest;
import com.webflux.example.globant_challenge.service.QuotationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuotationServiceTest {

    @Autowired
    private QuotationService quotationService;

    @Test
    public void testSaveQuotation() {
        QuotationRequest quotationRequest = new QuotationRequest();
        QuotationDataRequest quotationDataRequest = new QuotationDataRequest(
                "TUCSON", "BTC"
        );
        quotationRequest.setData(quotationDataRequest);
        assert quotationService.saveQuotation(quotationRequest).getModel().equals("TUCSON");
    }

}
