package com.karol.zadanie5.service;

import com.karol.zadanie5.model.Rate;

import java.util.List;

public interface RateService {
    List<Rate> getAllRates(Long id);

    Rate addRate(Rate rate, Long id);
}
