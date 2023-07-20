package com.webflux.example.globant_challenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class QuotationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void creacion_de_cotizacion_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/quotation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":{\"model\":\"SANTA_FE\",\"cryptoCurrency\":\"BTC\"}}"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void creacion_de_cotizacion_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/quotation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":{\"model\":\"SANTA_FE\",\"cryptoCurrency\":\"ABC\"}}"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

}
