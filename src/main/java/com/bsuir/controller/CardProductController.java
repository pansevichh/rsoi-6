package com.bsuir.controller;


import com.bsuir.domain.CardProduct;

import com.bsuir.service.CardProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CardProductController {
    private final CardProductService cardProductService;

    @GetMapping("/")
    public String hello(Model model) {
        return "hello";
    }


    @GetMapping("/catalog")
    public String getAll(Model model) {
        model.addAttribute("products", cardProductService.getCatalog());
        model.addAttribute("rates", cardProductService.getAverageRatesByCard(cardProductService.getCatalog()));
        return "product";
    }

    @GetMapping("catalog/sort")
    public String getAllSortedByRate(Model model) {
        model.addAttribute("rates", cardProductService.getAverageRatesByCard(cardProductService.getSortedCatalog()));
        model.addAttribute("products", cardProductService.getSortedCatalog());
        return "product";

    }

    @PostMapping("/catalog/add")
    public String addCardProduct(@ModelAttribute CardProduct cardProduct) {
        cardProductService.createCardProduct(cardProduct);
        return "redirect:/catalog";
    }

    @PostMapping("catalog/delete/{id}")
    public String deleteCardProduct(@PathVariable long id, Model model) {
        cardProductService.deleteCardProduct(id);
        model.addAttribute("products", cardProductService.getCatalog());
        return "redirect:/catalog";
    }




    @GetMapping("catalog/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("product", cardProductService.getCardProduct(id));
        return "product-edit";

    }

}
