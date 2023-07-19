package com.webflux.example.globant_challenge.client;

import com.webflux.example.globant_challenge.dto.external.response.SecondaryCCResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SecondaryCCClient {

    private final WebClient webClient;

    public SecondaryCCClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.coinlore.net/api/ticker").build();
    }

    public SecondaryCCResponse getBTCPrice() {
        return webClient.get().uri("/?id=90").
                retrieve().bodyToMono(new ParameterizedTypeReference<List<SecondaryCCResponse>>() {}).
                block().stream().collect(Collectors.toList()).get(0);
    }

    public SecondaryCCResponse getETHPrice() {
        return webClient.get().uri("/?id=80").
                retrieve().bodyToMono(new ParameterizedTypeReference<List<SecondaryCCResponse>>() {}).
                block().stream().collect(Collectors.toList()).get(0);
    }

}
