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

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ShopRepository shopRepository;

    public Product addNewObjectByBody(Product productSold){
        repository.save(productSold);
        return productSold;
    }

    public  Product addNewObjectByPath(String name, Double price, Integer id_shop) throws NotFoundException {

        Optional<Shop> x = shopRepository.findById(id_shop);
        if(!x.isPresent()) {
            throw new NotFoundException();
        }


        Product product = new Product(x.get(), name, price);
        repository.save(product);

        return product;
    }

    public  Product deleteObjectById(Integer id) throws NotFoundException {

        Optional<Product> x = repository.findById(id);
        if(!x.isPresent()) {
            throw new NotFoundException();
        }
        repository.delete(x.get());
        return  x.get();

    }

    public  Product findById(Integer id) throws NotFoundException {
        Optional<Product> x = repository.findById(id);
        if(!x.isPresent()) throw new NotFoundException();
        return x.get();
    }


    public List<Product> findByIdShop(Integer id) throws NotFoundException {
        Optional<Shop> x = shopRepository.findById(id);
        if(!x.isPresent()) {
            throw new NotFoundException();
        }
        List<Product> list = repository.findAll();
        List<Product> result = new ArrayList<>();
        for (Product product : list) {
            if(product.getShop().equals(x.get())){
                result.add(product);
            }
        }
        return result;
    }


    public Integer conuntHowManyProductIsTheSame(Product same){
        Integer result = 0;
        List<Product> list = repository.findAll();
        for (Product product : list) {
            if(product.equals(same)) {
                result++;
            }
        }
        return result;
    }

    public Integer countHowMuchProductCostMore(Double x) {
        Integer result = 0;
        List<Product> list = repository.findAll();
        for (Product product : list) {
            if(product.getPrice() > x){
                result++;
            }
        }
        return result;
    }


}
