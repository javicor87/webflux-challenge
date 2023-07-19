package com.webflux.example.globant_challenge.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CarBrandEnum {
    TUCSON("/1031"), ACCENT("/1036"), SANTA_FE("/1023"), I10("/1038");

    private String urlContext;
}
