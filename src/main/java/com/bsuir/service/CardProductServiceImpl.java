package com.bsuir.service;

import com.bsuir.domain.CardProduct;
import com.bsuir.repository.CardProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardProductServiceImpl implements CardProductService {
    private final CardProductRepo productRepo;
    private final SortProductService sortProductService;
    private final AverageRateService averageRateService;

    @Override
    public CardProduct getCardProduct(long id) {
        var cardProduct = productRepo.getReferenceById(id);
        return cardProduct;
    }

    @Override
    public boolean createCardProduct(CardProduct cardProduct) {
        productRepo.save(cardProduct);
        return true;
    }

    @Override
    public boolean updateCardProduct(CardProduct newCardProduct, long id) {
        var cardProduct = productRepo.getReferenceById(id);
        cardProduct.setProductName(newCardProduct.getProductName());
        cardProduct.setOrders(newCardProduct.getOrders());


        cardProduct.setRates(cardProduct.getRates());
        cardProduct.setPrice(newCardProduct.getPrice());
        var updatedProduct = productRepo.save(cardProduct);
        return true;
    }

    @Override
    public List<CardProduct> getCatalog() {
        var catalog = productRepo.findAll();
        return catalog;
    }

    @Override
    public void deleteCardProduct(long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Double getAverageRateByCard(CardProduct card) {
        AverageRateService av = new AverageRateService(productRepo);
        if(card.getRates().size()==0) return 0.0;
        return av.getAverageRate(card);
    }

    @Override
    public List<CardProduct> getSortedCatalog() {
        var catalog = sortProductService.sortCatalogByRate(productRepo.findAll());
        return catalog;
    }

    @Override
    public List<Double> getAverageRatesByCard(List<CardProduct> list) {
        AverageRateService av = new AverageRateService(productRepo);
        List<Double> res = new ArrayList<>();
        for (CardProduct product : list) {
            res.add(av.getAverageRate(product));
        }


        return res;
    }
}
