package com.bsuir.repository;

import com.bsuir.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {

}
