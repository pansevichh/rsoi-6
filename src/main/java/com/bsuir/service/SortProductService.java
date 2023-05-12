package com.bsuir.service;

import com.bsuir.domain.CardProduct;
import com.bsuir.domain.Rate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SortProductService {
    private final AverageRateService rateService;

    public List<CardProduct> sortCatalogByRate(List<CardProduct> productCatalog) {
        Comparator<CardProduct> comparator = Comparator.comparing(rateService::getAverageRate);
        Stream<CardProduct> sortedCatalog = productCatalog.stream()
                .sorted(comparator.reversed());
        return sortedCatalog.toList();
    }
}
