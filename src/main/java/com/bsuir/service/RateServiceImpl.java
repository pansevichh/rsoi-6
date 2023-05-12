package com.bsuir.service;

import com.bsuir.domain.CardProduct;
import com.bsuir.domain.Rate;
import com.bsuir.repository.CardProductRepo;
import com.bsuir.repository.RateRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class RateServiceImpl implements RateService {
    public final CardProductRepo productRepo;
    public final RateRepo rateRepo;

    @Override
    public void addRate(Rate r, long productId) {
        CardProduct cardProduct = productRepo.getReferenceById(productId);

        Rate rate = new Rate(r.getRate(), r.getDate().getDayOfMonth(),
                r.getDate().getMonthValue(), r.getDate().getYear());
        rate.setCardProduct(cardProduct);
        rateRepo.save(rate);

    }

    @Override
    public List<Rate> getRates(long productId) {
        CardProduct cardProduct = productRepo.getReferenceById(productId);
        List<Rate> rates = cardProduct.getRates();

        return rates;
    }

    @Override
    public List<Rate> getSortedRates(long productId) {
        CardProduct cardProduct = productRepo.getReferenceById(productId);
        Comparator<Rate> comparator = Comparator.comparing(Rate::getDate);
        List<Rate> rates = cardProduct.getRates().stream().sorted(comparator).toList();

        return rates;
    }
}
