package com.bsuir.config;

import com.bsuir.domain.CardProduct;
import com.bsuir.repository.CardProductRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CardProductRepo repository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            TypeReference<List<CardProduct>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/catalog.json");
            try {
                List<CardProduct> catalog = mapper.readValue(inputStream, typeReference);
                if (repository.count() == 0) {
                    repository.saveAll(catalog);
                    log.info("Products are saved!");
                }
            } catch (Exception e) {
                log.info("Unable to save products: " + e.getMessage());
            }
        };
    }
}
