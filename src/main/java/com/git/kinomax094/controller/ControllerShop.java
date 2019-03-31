package com.git.kinomax094.controller;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ControllerShop {

    @Autowired
    private ShopService service;

    @PostMapping("/addNewObjectByBody") // test
    public Shop addNewObjectByBody(@RequestBody Shop shop) {
        return service.addNewObjectByBody(shop);
    }

    //    {
//        "id": 6,
//            "name": "taa",
//            "city": "ddd",
//            "street": "ddd",
//            "number": "wwww"
//    }
    @PostMapping("/addNewObjectByPath/{name}/{city}/{street}/{number}") //test
    public Shop addNewObjectByPath(@PathVariable String name, @PathVariable String city, @PathVariable String street, @PathVariable String number) throws NotFoundException {
        return service.addNewObjectByPath(name, city, street, number);
    }

    @DeleteMapping("/deleteObjectById/{id}") // test
    public Shop deleteObjectById(@PathVariable Integer id) throws NotFoundException {
        return service.deleteObjectById(id);
    }

    @GetMapping("/findById/{id}") // test
    public Shop findById(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }

    @GetMapping("/shopWithBigestpPofit") // test
    public Shop shopWithBigestpPofit() throws NotFoundException {
        return service.shopWithBigestpPofit();
    }

    @GetMapping("/cityWithBigestNumberOfShop") // poprawka
    public String cityWithBigestNumberOfShop() {
        return service.cityWithBigestNumberOfShop();
    }

    @GetMapping("/findCustomerWithAmountOfOrdersBigestThatArg/{suma}") // test
    public List<Customer> findCustomerWithAmountOfOrdersBigestThatArg(@PathVariable double suma) {
        return service.findCustomerWithAmountOfOrdersBigestThatArg(suma);
    }

    @GetMapping("/findCustomerWithBigestSoldProduct") // test
    public String findCustomerWithBigestSoldProduct() {
        return service.findCustomerWithBigestSoldProduct();
    }
}
