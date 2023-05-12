package com.bsuir.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rateId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CardProduct cardProduct;

    @Column(name = "rate")
    private int rate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;

    public Rate() {
    }

    public Rate(int rate, int day, int month, int year) {
        this.rate = rate;
        this.date = LocalDate.of(year, month, day);
    }

    public Long getRateId() {
        return rateId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CardProduct getCardProduct() {
        return cardProduct;
    }

    public void setCardProduct(CardProduct cardProduct) {
        this.cardProduct = cardProduct;
    }

    public String toString() {
        return this.rate + ", " + this.date;
    }
}
