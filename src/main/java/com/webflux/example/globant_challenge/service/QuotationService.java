package com.webflux.example.globant_challenge.service;

import com.webflux.example.globant_challenge.client.ModelCarClient;
import com.webflux.example.globant_challenge.client.builder.CryptoCurrencyClientBuilder;
import com.webflux.example.globant_challenge.constant.CarBrandEnum;
import com.webflux.example.globant_challenge.constant.CryptoCurrencyEnum;
import com.webflux.example.globant_challenge.domain.Quotation;
import com.webflux.example.globant_challenge.domain.Version;
import com.webflux.example.globant_challenge.dto.external.response.ModelCarResponse;
import com.webflux.example.globant_challenge.dto.internal.request.QuotationRequest;
import com.webflux.example.globant_challenge.dto.internal.response.ModelCarInfoResponse;
import com.webflux.example.globant_challenge.repository.QuotationRepository;
import com.webflux.example.globant_challenge.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuotationService {

    private final ModelCarClient modelCarClient;

    private final CryptoCurrencyClientBuilder cryptoCurrencyClientBuilder;

    private final QuotationRepository quotationRepository;

    private final VersionRepository versionRepository;

    private final TransactionTemplate transactionTemplate;

    @Qualifier("jdbcScheduler")
    private final Scheduler jdbcScheduler;

    public List<ModelCarInfoResponse> getModelCars(@NotNull Optional<String> brand) {
        List<ModelCarResponse> modelCars;
        if (brand.isEmpty()) {
            modelCars = modelCarClient.findAllModelCars();
        } else {
            modelCars = getModelCars(brand.get());
        }
        return modelCars.stream().map(
                car -> new ModelCarInfoResponse(car.getVerCodigo(), car.getVerNombre()))
                .collect(Collectors.toList());
    }

    @Cacheable("lastModelQuotation")
    public Quotation saveQuotation(@NotNull QuotationRequest quotationRequest) {
        List<ModelCarResponse> modelCars = getModelCars(quotationRequest.getData().getModel());
        Double usdPrice = cryptoCurrencyClientBuilder.getCryptoCurrencyUsdPrice(quotationRequest.getData().getCryptoCurrency());
        Quotation quotation = new Quotation(
                null,
                UUID.randomUUID(),
                60,
                CryptoCurrencyEnum.valueOf(quotationRequest.getData().getCryptoCurrency()),
                quotationRequest.getData().getModel(),
                LocalDateTime.now(),
                null);
        List<Version> versions = modelCars.stream().map(
                car -> new Version(
                        null,
                        quotationRequest.getData().getModel(),
                        car.getVerNombre(),
                        car.getVeaPrecioPvp(),
                        car.getVeaPrecioPvp() / usdPrice,
                        CryptoCurrencyEnum.valueOf(quotationRequest.getData().getCryptoCurrency()),
                        quotation
                )).collect(Collectors.toList());
        quotation.setVersions(versions);
        versions.forEach(version -> version.setQuotation(quotation));
        Mono<Quotation> mQuotation = Mono.fromCallable(() -> transactionTemplate.execute(
                status -> {
                    Quotation q = quotationRepository.save(quotation);
                    versions.forEach(version -> versionRepository.save(version));
                    return q;
                }
        )).subscribeOn(jdbcScheduler);
        return mQuotation.block();
    }

    public Quotation getLastQuotation(String model) {
        return quotationRepository.findFirstByModelOrderByIdDesc(model);
    }

    private List<ModelCarResponse> getModelCars(String quotationRequest) {
        return switch (CarBrandEnum.valueOf(quotationRequest)) {
            case TUCSON -> modelCarClient.getAllNewTucsons();
            case ACCENT -> modelCarClient.getAllNewAccents();
            case SANTA_FE -> modelCarClient.getAllSantaFe();
            case I10 -> modelCarClient.getAllI10();
        };
    }

}
