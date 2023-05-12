package com.bsuir;

import com.bsuir.domain.CardProduct;
import com.bsuir.domain.Rate;
import com.bsuir.service.CardProductServiceImpl;
import com.bsuir.service.RateServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class ControllerTest {


    @Autowired
    private CardProductServiceImpl cardProductService;
    @Autowired
    private RateServiceImpl rateService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testRateInfo() throws Exception {
        CardProduct p = new CardProduct("qwe", 0);
        cardProductService.createCardProduct(p);


        mockMvc.perform(MockMvcRequestBuilders.get(("/product/rate/"+cardProductService.getCatalog().get(0).getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testControllerDeletePerform() throws Exception {
        CardProduct p = new CardProduct("qwe", 0);
        cardProductService.createCardProduct(p);


        mockMvc.perform(MockMvcRequestBuilders.post(("/catalog/delete/"+cardProductService.getCatalog().get(0).getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(302));
        List<CardProduct> list = cardProductService.getCatalog();
        assertTrue(list.size()==0);
    }

}
