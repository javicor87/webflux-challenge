package com.webflux.example.globant_challenge.client.builder;

import com.webflux.example.globant_challenge.client.PrimaryCCClient;
import com.webflux.example.globant_challenge.client.SecondaryCCClient;
import com.webflux.example.globant_challenge.constant.CryptoCurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Log4j2
@Component
public class CryptoCurrencyClientBuilder {

    private PrimaryCCClient primaryCCClient;

    private SecondaryCCClient secondaryCCClient;

    public Double getCryptoCurrencyUsdPrice(String cryptoCurrency) {
        Double usdPrice;
        try {
            usdPrice = switch (CryptoCurrencyEnum.valueOf(cryptoCurrency)) {
                case BTC -> primaryCCClient.getBTCPrice().getData().getLastPrice();
                case ETH -> primaryCCClient.getETHPrice().getData().getLastPrice();
            };
        } catch (Exception ex) {
            log.error("Error al obtener precio de fuente primaria.");
            usdPrice = switch (CryptoCurrencyEnum.valueOf(cryptoCurrency)) {
                case BTC -> secondaryCCClient.getBTCPrice().getPriceUsd();
                case ETH -> secondaryCCClient.getETHPrice().getPriceUsd();
            };
        };

        return usdPrice;
    }

}
