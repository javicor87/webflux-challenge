package com.webflux.example.globant_challenge.domain;

import com.webflux.example.globant_challenge.constant.CriptoCurrencyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "version")
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "version")
    private String version;

    @Column(name = "price_usd")
    private Double priceUsd;

    @Column(name = "price_crypto_currency")
    private Double priceCryptocurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "crypto_currency")
    private CriptoCurrencyEnum cryptoCurrency;

    @ManyToOne
    @JoinColumn(name="quotation_id", nullable=false)
    private Quotation quotation;

}
