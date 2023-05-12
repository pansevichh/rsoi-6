package com.bsuir.service;

import com.bsuir.domain.CardProduct;

import java.util.List;

public interface CardProductService {
    CardProduct getCardProduct(long id);

    boolean createCardProduct(CardProduct card);

    boolean updateCardProduct(CardProduct request, long id);

    List<CardProduct> getCatalog();

    void deleteCardProduct(long id);

    List<CardProduct> getSortedCatalog();

    List<Double> getAverageRatesByCard(List<CardProduct> list);

    Double getAverageRateByCard(CardProduct card);
}
