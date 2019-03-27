package com.git.kinomax094.service;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.repository.CustomerRepository;
import com.git.kinomax094.repository.ShopRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ShopRepository shopRepository;

    public Customer addNewObjectByBody(Customer customer){
       repository.save(customer);
       return customer;
    }

    public  Customer addNewObjectByPath(Integer shop_id, String name, String surname, String city, String street, String number) throws NotFoundException {
        Optional<Shop> x = shopRepository.findById(shop_id);
        if(!x.isPresent()) {
            throw  new NotFoundException();
        }
        Customer customer = new Customer(x.get(), name, surname, city, street, number);
        repository.save(customer);
        return customer;
    }

    public  Customer deleteObjectById(Integer id) throws NotFoundException {
        Optional<Customer> x = repository.findById(id);
        if(!x.isPresent()) {
            throw  new NotFoundException();
        }
        repository.delete(x.get());
        return x.get();
    }

    public  Customer findById(Integer id) throws NotFoundException {
        Optional<Customer> x = repository.findById(id);
        if(!x.isPresent()) {
            throw  new NotFoundException();
        }
        return x.get();
    }


    public  Double sumOfSoldProduct(Integer id) throws NotFoundException {
        Optional<Customer> x = repository.findById(id);
        if(!x.isPresent()) {
             throw  new NotFoundException();
        }
        Double suma = 0.0;
        for (ProductSold productSold : x.get().getProductSolds()) {
            suma += productSold.getPrice();
        }
        return suma;
    }

    public ProductSold returnTheExpensiveProduct(Integer id) throws NotFoundException {
        Optional<Customer> x = repository.findById(id);
        if(!x.isPresent()) {
            throw  new NotFoundException();
        }
        Double suma = 0.0;
        ProductSold result = null;
        for (ProductSold productSold : x.get().getProductSolds()) {
            if(suma < productSold.getPrice()) {
                suma = productSold.getPrice();
                result = productSold;
            }
        }
        return result;
    }

    public List<Customer> findAllCustomerBuyMoreThat(Double suma) throws NotFoundException {
        List<Customer> list = repository.findAll();
        List<Customer> result = new ArrayList<>();
        for (Customer customer : list) {
            if(sumOfSoldProduct(customer.getId()) > suma) {
                result.add(customer);
            }
        }
        return result;
    }

    public List<Customer> findAllCustomerBuyLessThat(Double suma) throws NotFoundException {
        List<Customer> list = repository.findAll();
        List<Customer> result = new ArrayList<>();
        for (Customer customer : list) {
            if(sumOfSoldProduct(customer.getId()) < suma) {
                result.add(customer);
            }
        }
        return  result;
    }

}
