package com.webflux.example.globant_challenge.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webflux.example.globant_challenge.constant.CriptoCurrencyEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "quotation")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "convertion_id")
    private UUID convertionId;

    @Column(name = "convertion_timelife")
    private int convertionTimelife;

    @Enumerated(EnumType.STRING)
    @Column(name = "crypto_currency")
    private CriptoCurrencyEnum cryptoCurrency;

    @Column(name = "model")
    private String model;

    @Column(name = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "quotation")
    private List<Version> versions;

}
