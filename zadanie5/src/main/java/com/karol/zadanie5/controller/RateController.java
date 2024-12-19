package com.karol.zadanie5.controller;

import com.karol.zadanie5.service.RateService;
import com.karol.zadanie5.model.Rate;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rate")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/{id}")
    public List<Rate> getRates(@Valid  @PathVariable Long id) {
        return rateService.getAllRates(id);
    }

    @PostMapping("/{id}")
    public Rate addRate(@Valid @RequestBody Rate rate, @PathVariable Long id) {
        return rateService.addRate(rate, id);
    }
}
