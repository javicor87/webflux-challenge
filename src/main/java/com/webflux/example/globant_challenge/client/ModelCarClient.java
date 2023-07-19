package com.webflux.example.globant_challenge.client;

import com.webflux.example.globant_challenge.constant.CarBrandEnum;
import com.webflux.example.globant_challenge.dto.external.response.ModelCarResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ModelCarClient {

    @Value("${application.base-url.hyundai}")
    private String baseUrl;

    private final WebClient webClient;

    public ModelCarClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://kerner.hyundai.com.ec/api/versiones/1").build();
    }

    public List<ModelCarResponse> getAllNewTucsons() {
        return webClient.get().uri(CarBrandEnum.TUCSON.getUrlContext()).
                retrieve().bodyToMono(new ParameterizedTypeReference<List<ModelCarResponse>>() {}).
                cache(Duration.ofHours(1)).
                block().stream().collect(Collectors.toList());
    }

    public ModelCarResponse getTucson(int codigo) {
        Flux<ModelCarResponse> tucsonFlux = webClient.get()
                .uri(CarBrandEnum.TUCSON.getUrlContext())
                .retrieve()
                .bodyToFlux(ModelCarResponse.class);
        List<ModelCarResponse> tucsonList = tucsonFlux.toStream().filter(car -> car.getVerCodigo() == codigo).collect(Collectors.toList());
        if (!tucsonList.isEmpty()) {
            return tucsonList.get(0);
        } else {
            return null;
        }
    }

    public List<ModelCarResponse> getAllNewAccents() {
        return webClient.get().uri(CarBrandEnum.ACCENT.getUrlContext()).
                retrieve().bodyToMono(new ParameterizedTypeReference<List<ModelCarResponse>>() {}).
                cache(Duration.ofHours(1)).
                block();
    }

    public List<ModelCarResponse> getAllSantaFe() {
        return webClient.get().uri(CarBrandEnum.SANTA_FE.getUrlContext()).
                retrieve().bodyToMono(new ParameterizedTypeReference<List<ModelCarResponse>>() {}).
                cache(Duration.ofHours(1)).
                block();
    }

    public List<ModelCarResponse> getAllI10() {
        return webClient.get().uri(CarBrandEnum.I10.getUrlContext()).
                retrieve().bodyToMono(new ParameterizedTypeReference<List<ModelCarResponse>>() {}).
                cache(Duration.ofHours(1)).
                block();
    }

    public List<ModelCarResponse> findAllModelCars() {
        List<ModelCarResponse> allModelCars = new ArrayList<>();
        allModelCars.addAll(getAllNewTucsons());
        allModelCars.addAll(getAllNewAccents());
        allModelCars.addAll(getAllSantaFe());
        allModelCars.addAll(getAllI10());
        return  allModelCars;
    }

}
