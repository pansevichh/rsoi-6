package com.bsuir.controller;

import com.bsuir.domain.CardProduct;
import com.bsuir.domain.Rate;

import com.bsuir.service.CardProductService;
import com.bsuir.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor()
public class RateController {
    private final RateService rateService;
    private final CardProductService cardProductService;


    @GetMapping("product/rate/{id}")
    public String getAll(@PathVariable long id, Model model) {
        CardProduct cardProduct = cardProductService.getCardProduct(id);
        model.addAttribute("product", cardProductService.getCardProduct(id));
        model.addAttribute("rates", rateService.getRates(id));
        model.addAttribute("average", cardProductService.getAverageRateByCard(cardProduct));
        Rate rate = new Rate();
        rate.setCardProduct(cardProduct);
        model.addAttribute("rate", rate);
        return "card-rate";
    }

    @PostMapping("/rates/{id}")
    public String addRate(@ModelAttribute("rate") Rate rate, @PathVariable long id) {
        rateService.addRate(rate, id);
        return "redirect:/product/rate/" + id;
    }

    @GetMapping("product/sort/{id}")
    public String getAllSortedByDAte(@PathVariable long id, Model model) {
        CardProduct cardProduct = cardProductService.getCardProduct(id);
        model.addAttribute("product", cardProductService.getCardProduct(id));
        model.addAttribute("average", cardProductService.getAverageRateByCard(cardProduct));

        model.addAttribute("rates", rateService.getSortedRates(id));
        Rate rate = new Rate();
        rate.setCardProduct(cardProduct);
        model.addAttribute("rate", rate);
        return "card-rate";

    }


}
