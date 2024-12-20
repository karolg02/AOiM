package com.karol.zadanie5.controller;

import com.karol.zadanie5.exceptions.Exceptions;
import com.karol.zadanie5.model.ClassTeacher;
import com.karol.zadanie5.model.Rate;
import com.karol.zadanie5.service.RateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RateController.class)
class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RateService rateService;

    private Rate rate;

    @BeforeEach
    void setup() {
        rate = new Rate();
        ClassTeacher classTeacher = new ClassTeacher();
        classTeacher.setId(1L);
        rate.setId(1L);
        rate.setValue(5);
        rate.setDate(LocalDate.now());
        rate.setGroup(classTeacher);
        rate.setComment("elo");
    }

    //@Test
    void shouldGetRates() throws Exception {
        List<Rate> rates = List.of(rate);
        Mockito.when(rateService.getAllRates(1L)).thenReturn(rates);

        mockMvc.perform(get("/api/rate/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].value").value(5));
    }

    //@Test
    void shouldAddRate() throws Exception {
        Mockito.when(rateService.addRate(any(Rate.class), eq(1L))).thenReturn(rate);

        String rateJson = """
            {
                "value": 5,
                "comment": "Zajebista rzecz"
            }
            """;

        mockMvc.perform(post("/api/rate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(rateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.value").value(5))
                .andExpect(jsonPath("$.comment").value("Zajebista rzecz"))
                .andExpect(jsonPath("$.date").isNotEmpty());
    }

    @Test
    void shouldReturnNotFoundWhenClassTeacherDoesNotExist() throws Exception {
        Mockito.when(rateService.getAllRates(99L))
                .thenThrow(Exceptions.ResourceNotFoundException.class);

        mockMvc.perform(get("/api/rate/99"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldReturnBadRequestForInvalidRate() throws Exception {
        String invalidRateJson = """
                {
                    "value": null
                }
                """;

        mockMvc.perform(post("/api/rate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRateJson))
                .andExpect(status().isBadRequest());
    }
}
