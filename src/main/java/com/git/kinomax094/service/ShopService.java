package com.git.kinomax094.service;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository repository;

    public Shop addNewObjectByBody(Shop shop){
        repository.save(shop);
        return shop;
    }

    public  Shop addNewObjectByPath(String name, String city, String street, String number) {
        Shop shop = new Shop(name, city, street, number);
        repository.save(shop);
        return shop;
    }

    public  Shop deleteObjectById(Integer id) throws NotFoundException {
        Optional<Shop> x = repository.findById(id);
        if(!x.isPresent()) {
            throw  new NotFoundException();
        }
        repository.delete(x.get());
        return  x.get();
    }

    public  Shop findById(Integer id) throws NotFoundException {
        Optional<Shop> x = repository.findById(id);
        if(!x.isPresent()) {
            throw  new NotFoundException();
        }
        return  x.get();
    }


    public Shop shopWithBigestpPofit() {
        Shop result = null;
        double max = 0;
        double suma = 0;
        List<Shop> list = repository.findAll();

        for (Shop shop : list) {
            for (ProductSold productSold : shop.getProductSolds()) {
                suma += productSold.getPrice();
            }
            if(suma > max) {
                max = suma;
                suma = 0;
                result = shop;
            }
        }
        return result;
    }



    public String cityWithBigestNumberOfShop() {
        List<Shop> shops = repository.findAll();
        Map<String, Integer> map = new HashMap<>();
        for (Shop shop : shops) {
            if (map.containsKey(shop.getName())) {
                map.put(shop.getName(), map.get(shop.getName()) + 1);
            } else {
                map.put(shop.getName(), 1);
            }
        }
        int max = 0;
        String result = "";
        for (String s : map.keySet()) {
            if (map.get(s) > max) {
                max = map.get(s);
                result = s;
            }
        }

        return result;

    }


    public List<Customer> findCustomerWithAmountOfOrdersBigestThatArg(double x) {
        List<Shop> shops = repository.findAll();
        List<Customer> result = new ArrayList<Customer>();
        double suma = 0;
        for (Shop shop : shops) {
            for (Customer customer : shop.getCustomers()) {
                for (ProductSold productSold : customer.getProductSolds()) {
                    suma += productSold.getPrice();
                }
                if(suma > x) {
                    result.add(customer);
                }
                suma = 0;
            }
        }
        return result;
    }

    public String findCustomerWithBigestSoldProduct() {
        Customer result = null;
        double max = 0;
        double suma = 0;
        List<Customer> list = repository.findAll().stream().map(n -> n.getCustomers()).
                flatMap(n-> n.stream()).
                collect(Collectors.toList());
        for (Customer customer : list) {
            for (ProductSold productSold : customer.getProductSolds()) {
                suma += productSold.getPrice();
            }
            if(suma > max) {
                max = suma;
                result = customer;
            }
        }


        return result.getName() + " " + result.getSurname();
    }

}
