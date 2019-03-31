package com.git.kinomax094.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    String name;

    String city;

    String street;

    String number;


    @JsonBackReference()
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<ProductSold> productSolds = new ArrayList<>();

    public Shop(String name, String city, String street, String number) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Customer> customers = new ArrayList<>();


    @JsonBackReference
    @OneToMany(mappedBy = "shop", cascade = CascadeType.REMOVE)
    List<Product> products = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<ProductSold> getProductSolds() {
        return productSolds;
    }

    public void setProductSolds(List<ProductSold> productSolds) {
        this.productSolds = productSolds;
    }

    public Shop() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name) &&
                Objects.equals(city, shop.city) &&
                Objects.equals(street, shop.street) &&
                Objects.equals(number, shop.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, street, number);
    }
}
