package com.webflux.example.globant_challenge.service;

import com.webflux.example.globant_challenge.domain.Purchase;
import com.webflux.example.globant_challenge.domain.Quotation;
import com.webflux.example.globant_challenge.dto.internal.request.PurchaseReportRequest;
import com.webflux.example.globant_challenge.dto.internal.request.PurchaseRequest;
import com.webflux.example.globant_challenge.dto.internal.request.QuotationDataRequest;
import com.webflux.example.globant_challenge.dto.internal.request.QuotationRequest;
import com.webflux.example.globant_challenge.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final QuotationService quotationService;

    private final TransactionTemplate transactionTemplate;

    @Qualifier("jdbcScheduler")
    private final Scheduler jdbcScheduler;

    public Purchase savePurchase(PurchaseRequest purchaseRequest) {
        Quotation quotation = quotationService.saveQuotation(new QuotationRequest(
                new QuotationDataRequest(
                purchaseRequest.getData().getModel(),
                purchaseRequest.getData().getCryptoCurrency()
        )));
        Purchase purchase = new Purchase(
                null,
                UUID.randomUUID(),
                purchaseRequest.getData().getFullName(),
                LocalDateTime.now(),
                quotation
        );
        Mono<Purchase> mPurchase = Mono.fromCallable(() -> transactionTemplate.execute(
                status -> purchaseRepository.save(purchase)
        )).subscribeOn(jdbcScheduler);
        return mPurchase.block();
    }

    public List<Purchase> getPurchaseReport(PurchaseReportRequest purchaseReportRequest) {
        List<Purchase> purchases = purchaseRepository.findAllWithCreationDateTimeBefore(
                purchaseReportRequest.getData().getDate().atStartOfDay());
        return purchases.stream().filter(p -> p.getQuotation().getModel().equals(purchaseReportRequest.getData().getModel())
                && p.getQuotation().getCryptoCurrency().name().equals(purchaseReportRequest.getData().getCryptoCurrency()))
                .collect(Collectors.toList());
    }

}
