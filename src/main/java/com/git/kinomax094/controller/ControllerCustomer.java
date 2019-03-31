package com.git.kinomax094.controller;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.Product;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class ControllerCustomer {

    @Autowired
    private CustomerService service;

    @PostMapping("/addNewObjectByBody") // test
    public Customer addNewObjectByBody(@RequestBody Customer customer) {
        return service.addNewObjectByBody(customer);
    }
//    {
//        "name": "Ewelina",
//            "surname": "Stan",
//            "city": "Warszawa",
//            "street": "Wodzickiego",
//            "number": "51"
//    }


    @PostMapping("/addNewObjectByPath/{name}/{surname}/{id_shop}") // test
    public Customer addNewObjectByPath(@PathVariable String name, @PathVariable String surname, @PathVariable Integer id_shop) throws NotFoundException {
        return service.addNewObjectByPath(id_shop, name, surname, null, null, null);
    }

    @DeleteMapping("/deleteObjectById/{id}") // test
    public Customer deleteObjectById(@PathVariable Integer id) throws NotFoundException {
        return service.deleteObjectById(id);
    }

    @GetMapping("/findById/{id}")  // test
    public Customer findById(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }

    @GetMapping("/sumOfSoldProduct/{id}") //test
    public Double sumOfSoldProduct(@PathVariable Integer id) throws NotFoundException {
        return service.sumOfSoldProduct(id);
    }

    @GetMapping("/returnTheExpensiveSoldProductFromCustomer/{id}") // test
    public ProductSold returnTheExpensiveProductFromSolded(@PathVariable Integer id) throws NotFoundException {
        return service.returnTheExpensiveProduct(id);
    }

    @GetMapping("/returnTheExpensiveProduct/{suma}") // test
    public List<Customer> findAllCustomerBuyMoreThat(@PathVariable Double suma) throws NotFoundException {
        return service.findAllCustomerBuyMoreThat(suma);
    }

    @GetMapping("/findAllCustomerBuyLessThat/{suma}") //test
    public List<Customer> findAllCustomerBuyLessThat(@PathVariable Double suma) throws NotFoundException {
        return service.findAllCustomerBuyLessThat(suma);
    }

}
