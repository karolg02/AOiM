package com.karol.zadanie5.Controller;

import com.karol.zadanie5.Service.RateService;
import com.karol.zadanie5.model.Rate;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rate")
public class RateController {
    private RateService rateService;

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
