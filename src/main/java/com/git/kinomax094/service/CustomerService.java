package com.git.kinomax094.service;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.repository.CustomerRepository;
import com.git.kinomax094.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ShopRepository shopRepository;

    public Customer addNewObjectByBody(Customer customer) {
        repository.save(customer);
        return customer;
    }

    public Customer addNewObjectByPath(Integer shop_id, String name, String surname, String city, String street, String number) throws NotFoundException {
        Optional<Shop> x = shopRepository.findById(shop_id);
        if (!x.isPresent()) {
            throw new NotFoundException();
        }
        Customer customer = new Customer(x.get(), name, surname, city, street, number);
        repository.save(customer);
        return customer;
    }

    public Customer deleteObjectById(Integer id) throws NotFoundException {
        Optional<Customer> x = repository.findById(id);
        if (!x.isPresent()) {
            throw new NotFoundException();
        }
        repository.delete(x.get());
        return x.get();
    }

    public Customer findById(Integer id) throws NotFoundException {
        Optional<Customer> x = repository.findById(id);
        if (!x.isPresent()) {
            throw new NotFoundException();
        }
        return x.get();
    }


    public Double sumOfSoldProduct(Integer id) throws NotFoundException {

        Optional<Customer> x = repository.findById(id);

        if (!x.isPresent()) {
            throw new NotFoundException();
        }

        Double sum = x.get().getProductSolds().stream().mapToDouble(ProductSold::getPrice).sum();

        return sum;
    }

    public ProductSold returnTheExpensiveProduct(Integer id) throws NotFoundException {

        Optional<Customer> x = repository.findById(id);
        ProductSold result;

        if (!x.isPresent()) {
            throw new NotFoundException();
        } else {
            Optional<ProductSold> optionalResult = x.get().getProductSolds().stream().max((o1, o2) -> o1.getPrice() > o2.getPrice() ? 1 : -1);
            if (!optionalResult.isPresent()) {
                throw new NotFoundException();
            } else {
                result = optionalResult.get();
            }
        }
        return result;

    }

    public List<Customer> findAllCustomerBuyMoreThat(Double sum) {

        List<Customer> result = repository.findAll().stream()
                .filter(customer -> customer.getProductSolds().stream().mapToDouble(ProductSold::getPrice).sum() > sum)
                .collect(Collectors.toList());

        return result;
    }

    public List<Customer> findAllCustomerBuyLessThat(Double sum) {

        List<Customer> result = repository.findAll().stream()
                .filter(customer -> customer.getProductSolds().stream().mapToDouble(ProductSold::getPrice).sum() < sum)
                .collect(Collectors.toList());

        return result;
    }

}
