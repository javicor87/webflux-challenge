package com.webflux.example.globant_challenge.client.builder;

import com.webflux.example.globant_challenge.client.PrimaryCCClient;
import com.webflux.example.globant_challenge.client.SecondaryCCClient;
import com.webflux.example.globant_challenge.constant.CriptoCurrencyEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CryptoCurrencyClientBuilder {

    private PrimaryCCClient primaryCCClient;

    private SecondaryCCClient secondaryCCClient;

    public Double getCryptoCurrencyUsdPrice(String cryptoCurrency) {
        Double usdPrice;
        try {
            usdPrice = switch (CriptoCurrencyEnum.valueOf(cryptoCurrency)) {
                case BTC -> primaryCCClient.getBTCPrice().getData().getLastPrice();
                case ETH -> primaryCCClient.getETHPrice().getData().getLastPrice();
            };
        } catch (Exception ex) {
            usdPrice = switch (CriptoCurrencyEnum.valueOf(cryptoCurrency)) {
                case BTC -> secondaryCCClient.getBTCPrice().getPriceUsd();
                case ETH -> secondaryCCClient.getETHPrice().getPriceUsd();
            };
        };

        return usdPrice;
    }

}
