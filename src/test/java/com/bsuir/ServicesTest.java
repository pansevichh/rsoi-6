package com.bsuir;

import com.bsuir.domain.CardProduct;
import com.bsuir.domain.Rate;
import com.bsuir.repository.CardProductRepo;
import com.bsuir.repository.RateRepo;
import com.bsuir.service.CardProductServiceImpl;
import com.bsuir.service.RateServiceImpl;
import com.bsuir.service.SortProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ServicesTest {
    @Autowired
    private CardProductRepo productRepo;
    @Autowired
    private SortProductService sortProductService;
    @Autowired
    private CardProductServiceImpl cardProductService;
    @Autowired
    private RateServiceImpl rateService;
    @Autowired
    private RateRepo rateRepo;


    @Test
    public void testSortEmpty() throws Exception {
        List<CardProduct> list = productRepo.findAll();
        list = sortProductService.sortCatalogByRate(list);
        List<CardProduct> list2 = productRepo.findAll();
        list2 = sortProductService.sortCatalogByRate(list);
        boolean fl = false;
        if (list.size() == list2.size()) {
            fl = true;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() != list2.get(i).getId()) {
                    fl = false;
                    break;
                }
            }
        }
        assertTrue(fl);

    }


    @Test
    public void testGetProduct() throws Exception {

        List<CardProduct> list = productRepo.findAll();
        List<CardProduct> list2 = cardProductService.getCatalog();

        assertTrue(list.size() == list2.size());

    }

    @Test
    public void testAddRate() throws Exception {
        CardProduct p = new CardProduct("qwe", 99);
        cardProductService.createCardProduct(p);

        Rate r = new Rate(10, 9, 8, 2009);
        rateService.addRate(r, cardProductService.getCatalog().get(0).getId());
       List<Rate> list = rateRepo.findAll();
        assertTrue(list.size()==1);

    }



}
