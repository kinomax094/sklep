package com.git.kinomax094.service;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.repository.CustomerRepository;
import com.git.kinomax094.repository.ShopRepository;
import com.git.kinomax094.repository.SoldProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSoldService {

    @Autowired
    private SoldProductRepository repository;

    @Autowired
    private CustomerRepository repositoryCustomer;

    @Autowired
    private ShopRepository shopRepository;


    public ProductSold addNewObjectByBody(ProductSold productSold){
        repository.save(productSold);
        return productSold;
    }

    public  ProductSold addNewObjectByPath(String name, Double price, Integer id_shop, Integer id_costomer) throws NotFoundException {
        Optional<Shop> x = shopRepository.findById(id_shop);
        if(!x.isPresent()) {
            throw new NotFoundException();
        }

        Optional<Customer> y = repositoryCustomer.findById(id_costomer);
        if(!y.isPresent()) {
            throw new NotFoundException();
        }

        ProductSold productSold = new ProductSold(x.get(), y.get(), name, price);
        repository.save(productSold);

        return productSold;
    }

    public  ProductSold deleteObjectById(Integer id) throws NotFoundException {
        Optional<ProductSold> x = repository.findById(id);
        if(!x.isPresent()) {
            throw new NotFoundException();
        }
        repository.delete(x.get());
        return  x.get();
    }

    public  ProductSold findById(Integer id) throws NotFoundException {
        Optional<ProductSold> x = repository.findById(id);
        if(!x.isPresent()) throw new NotFoundException();
        return x.get();
    }

    public List<ProductSold> findByIdCustomer(Integer id) throws NotFoundException {
        Optional<Customer> x = repositoryCustomer.findById(id);
        if(!x.isPresent()) {
            throw new NotFoundException();
        }
        List<ProductSold> list = repository.findAll();
        List<ProductSold> result = new ArrayList<>();
        for (ProductSold productSold : list) {
            if(productSold.getCustomer().getId() == id){
                result.add(productSold);
            }
        }
        return result;
    }

    public List<ProductSold> findByIdShop(Integer id) throws NotFoundException {
        Optional<Shop> x = shopRepository.findById(id);
        if(!x.isPresent()) {
            throw new NotFoundException();
        }
        List<ProductSold> list = repository.findAll();
        List<ProductSold> result = new ArrayList<>();
        for (ProductSold productSold : list) {
            if(productSold.getShop().getId() == id){
                result.add(productSold);
            }
        }
        return result;
    }


    public Integer conuntHowManyProductIsTheSame(ProductSold product) {
        Integer result = 0;
        List<ProductSold> list = repository.findAll();
        for (ProductSold productSold : list) {
            if(product.equals(productSold)) {
                result++;
            }
        }
        return result;
    }

    public Integer countHowMuchProductCostMore(Double x) {
        Integer result = 0;
        List<ProductSold> list = repository.findAll();
        for (ProductSold productSold : list) {
            if(productSold.getPrice() > x){
                result++;
            }
        }
        return result;
    }

}
