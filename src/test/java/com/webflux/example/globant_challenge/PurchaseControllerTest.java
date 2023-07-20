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
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void creacion_de_compra_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":{\"convertionId\":\"57dfc4e5-6a6d-4b9f-90e1-007b0943402f\",\"fullName\":\"Javier Coronel\",\"version\":\"TUCSON TL\",\"model\":\"TUCSON\",\"cryptoCurrency\":\"ETH\"}}"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void creacion_de_compra_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":{\"convertionId\":\"57dfc4e5-6a6d-4b9f-90e1-007b0943402f\",\"fullName\":\"Javier Coronel\",\"version\":\"TUCSON TL\",\"model\":\"TUCSON\",\"cryptoCurrency\":\"ABC\"}}"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    public void generar_reporte_200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":{\"date\":\"2023-07-19\",\"model\":\"TUCSON\",\"cryptoCurrency\":\"ETH\"}}"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk()).
                andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void generar_reporte_400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":{\"date\":\"2023-07-19\",\"model\":\"TUCSON\",\"cryptoCurrency\":\"ABC\"}}"))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

}
