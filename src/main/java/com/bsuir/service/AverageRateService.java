package com.bsuir.service;

import com.bsuir.domain.CardProduct;
import com.bsuir.domain.Rate;
import com.bsuir.repository.CardProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class AverageRateService {
    public final CardProductRepo productRepo;
    public final double normalAdequacyIndex = 0.08;

    public double getAverageRate(CardProduct cardProduct) {
        double averageWeightedRate = getWeightedRate(cardProduct);

        double priceIndex = cardProduct.getPrice() / getMarketPrice();

        double adequacyIndex = (double) cardProduct.getRates().size() / (double) cardProduct.getOrders();
        double averageRate;

        if (adequacyIndex <= normalAdequacyIndex) {
            averageRate = averageWeightedRate / (priceIndex - adequacyIndex);
        } else {

            averageRate = averageWeightedRate / (priceIndex + adequacyIndex);
        }


        return getFormattedRate(averageRate);
    }

    public double getWeightedRate(CardProduct product) {
        double weightedRate = 0;

        Comparator<Rate> comparator = Comparator.comparing(Rate::getDate);
        if (!product.getRates().isEmpty()) {
            List<Rate> sortedRates = product.getRates().stream().sorted(comparator).toList();

            int weight = 0;
            int i = 1;
            for (Rate rate : sortedRates) {
                weightedRate += i * rate.getRate();
                weight += i;
                i++;
            }
            weightedRate /= weight;
        } else weightedRate = 0;

        return weightedRate;
    }

    public double getMarketPrice() {
        return productRepo.findAll().stream().mapToDouble(CardProduct::getPrice).sum() / productRepo.count();
    }

    public double getFormattedRate(double rate) {

        if (rate > 5) {
            rate = 5.0;
        }
        if (rate < 0) {
            rate = 0;
        }
        BigDecimal bd = new BigDecimal(rate).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
