package com.git.kinomax094.service;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.Product;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.repository.ProductRepository;
import com.git.kinomax094.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ShopRepository shopRepository;

    public Product addNewObjectByBody(Product productSold) {
        repository.save(productSold);
        return productSold;
    }

    public Product addNewObjectByPath(String name, Double price, Integer id_shop) throws NotFoundException {

        Optional<Shop> x = shopRepository.findById(id_shop);
        if (!x.isPresent()) {
            throw new NotFoundException();
        }


        Product product = new Product(x.get(), name, price);
        repository.save(product);

        return product;
    }

    public Product deleteObjectById(Integer id) throws NotFoundException {

        Optional<Product> x = repository.findById(id);
        if (!x.isPresent()) {
            throw new NotFoundException();
        }
        repository.delete(x.get());
        return x.get();

    }

    public Product findById(Integer id) throws NotFoundException {
        Optional<Product> x = repository.findById(id);
        if (!x.isPresent()) throw new NotFoundException();
        return x.get();
    }


    public List<Product> findByIdShop(Integer id) throws NotFoundException {

        Optional<Shop> x = shopRepository.findById(id);

        if (!x.isPresent()) {
            throw new NotFoundException();
        }

        List<Product> list = repository.findAll().stream().filter(product -> product.getShop().equals(x.get())).collect(Collectors.toList());

        return list;
    }


    public Integer conuntHowManyProductIsTheSame(Product same) {

        Integer result = Math.toIntExact(repository.findAll().stream().filter(product -> product.equals(same)).count());

        return result;
    }

    public Integer countHowMuchProductCostMore(Double x) {

        Integer result = Math.toIntExact(repository.findAll().stream().filter(product -> product.getPrice() > x).count());
        List<Product> list = repository.findAll();

        return result;
    }


}
