package com.bsuir.repository;

import com.bsuir.domain.CardProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardProductRepo extends JpaRepository<CardProduct, Long> {
}
