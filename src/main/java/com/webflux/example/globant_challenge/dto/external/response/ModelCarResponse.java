package com.webflux.example.globant_challenge.dto.external.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class ModelCarResponse {
    private int verCodigo;
    private String verNombre;
    private int veaAnio;
    private Double veaPrecioPvp;
    private int veaBono;
    private int veaPrecioFinal;
    private int veaDiscapacidad100;
    private int verCodSgc;

}
