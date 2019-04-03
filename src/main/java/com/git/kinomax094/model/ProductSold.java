package com.git.kinomax094.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductSold {

    public ProductSold() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonManagedReference()
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Shop shop;

    @JsonManagedReference()
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Customer customer;

    String name;

    Double price;

    public ProductSold(Shop shop, Customer customer, String nazwa, Double price) {
        this.shop = shop;
        this.customer = customer;
        this.name = nazwa;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getNazwa() {
        return name;
    }

    public void setNazwa(String nazwa) {
        this.name = nazwa;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSold)) return false;
        ProductSold that = (ProductSold) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }


}
