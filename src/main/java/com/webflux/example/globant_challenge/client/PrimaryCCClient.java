package com.webflux.example.globant_challenge.client;

import com.webflux.example.globant_challenge.dto.external.response.PrimaryCCResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class PrimaryCCClient {

    private final WebClient webClient;

    public PrimaryCCClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://http-api.livecoinwatch.com/coins/").build();
    }

    public PrimaryCCResponse getBTCPrice() {
        return webClient.get().uri("/BTC/about?currency=USD").
                retrieve().bodyToMono(PrimaryCCResponse.class).
                block();
    }

    public PrimaryCCResponse getETHPrice() {
        return webClient.get().uri("/ETH/about?currency=USD").
                retrieve().bodyToMono(PrimaryCCResponse.class).
                block();
    }

}
