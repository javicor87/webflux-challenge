package com.webflux.example.globant_challenge;

import com.webflux.example.globant_challenge.dto.internal.request.PurchaseDataRequest;
import com.webflux.example.globant_challenge.dto.internal.request.PurchaseReportDataRequest;
import com.webflux.example.globant_challenge.dto.internal.request.PurchaseReportRequest;
import com.webflux.example.globant_challenge.dto.internal.request.PurchaseRequest;
import com.webflux.example.globant_challenge.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

    @Test
    public void createPurchase() {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        PurchaseDataRequest purchaseDataRequest = new PurchaseDataRequest(
                UUID.randomUUID(),
                "TestNombre TestApellido",
                "TUCSON TL",
                "TUCSON",
                "BTC"
        );
        purchaseRequest.setData(purchaseDataRequest);
        assert purchaseService.savePurchase(purchaseRequest).getClientName().equals("TestNombre TestApellido");
    }

    @Test
    public void testPurchaseReport() {

        PurchaseReportRequest purchaseReportRequest = new PurchaseReportRequest();
        PurchaseReportDataRequest purchaseReportDataRequest = new PurchaseReportDataRequest(
                LocalDate.now(),
                "TUCSON",
                "BTC"
        );
        purchaseReportRequest.setData(purchaseReportDataRequest);
        assert purchaseService.getPurchaseReport(purchaseReportRequest)
                .get(0).getQuotation().getModel().equals("TUCSON");
    }

}
