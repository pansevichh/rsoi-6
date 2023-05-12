package com.bsuir.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class CardProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product")
    private String productName;

    @OneToMany(mappedBy = "cardProduct", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rate> rates;

    @Column(name = "orders")
    private int orders;

    @Column(name = "price")
    private double price;

    public CardProduct() {
    }

    public CardProduct(String productName, int orders) {
        this.productName = productName;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
        rates.forEach(rate -> rate.setCardProduct(this));
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int numberOfOrders) {
        this.orders = numberOfOrders;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "CardProduct{" + "id=" + this.id + ", name='" + this.productName + "', orders=" + this.orders + ", price=" + this.price + "}";
    }
}
