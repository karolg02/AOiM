package com.karol.zadanie5.Service;

import com.karol.zadanie5.model.Rate;

import java.util.List;

public interface RateService {
    List<Rate> getAllRates(Long id);

    Rate addRate(Rate rate, Long id);
}
