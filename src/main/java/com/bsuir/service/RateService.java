package com.bsuir.service;

import com.bsuir.domain.Rate;

import java.util.List;

public interface RateService {

    void addRate(Rate request, long productId);

    List<Rate> getRates(long productId);

    List<Rate> getSortedRates(long productId);
}
