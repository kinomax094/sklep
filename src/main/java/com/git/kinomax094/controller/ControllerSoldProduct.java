package com.git.kinomax094.controller;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.repository.SoldProductRepository;
import com.git.kinomax094.service.ProductSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soldProduct")
public class ControllerSoldProduct {

    @Autowired
    private ProductSoldService service;

    @PostMapping("/addNewObjectByBody") // test
    public ProductSold addNewObjectByBody(@RequestBody ProductSold productSold){
        return  service.addNewObjectByBody(productSold);
    }
//    {
//        "customer": {
//        "id": 3,
//                "shop": {
//            "id": 1,
//                    "name": "Warszawa",
//                    "city": "Extra Pamiątki",
//                    "street": "68",
//                    "number": "Wodzickiego"
//        },
//        "name": "Ewa",
//                "surname": "Byczkowska",
//                "city": "Warszawa",
//                "street": "Ludowa",
//                "number": "83"
//    },
//        "price": 32,
//            "nazwa": "name"
//    }


    @PostMapping("/addNewObjectByPath/{name}/{price}/{id_shop}/{id_costomer}") // test
    public  ProductSold addNewObjectByPath(@PathVariable String name,@PathVariable Double price,@PathVariable Integer id_shop,@PathVariable Integer id_costomer) throws NotFoundException {
        return service.addNewObjectByPath(name, price, id_shop, id_costomer);
    }
    @DeleteMapping("/deleteObjectById/{id}") // test
    public  ProductSold deleteObjectById(@PathVariable Integer id) throws NotFoundException {
        return  service.deleteObjectById(id);
    }
    @GetMapping("/findById/{id}") //test
    public  ProductSold findById(@PathVariable  Integer id) throws NotFoundException {
        return  service.findById(id);
    }
    @GetMapping("/findByIdCustomer/{id}") // test
    public List<ProductSold> findByIdCustomer(@PathVariable Integer id) throws NotFoundException {
        return service.findByIdCustomer(id);
    }

    @GetMapping("/findByIdShop/{id}") // test
    public List<ProductSold> findByIdShop(@PathVariable Integer id) throws NotFoundException {
        return service.findByIdShop(id);
    }

    @GetMapping("/conuntHowManyProductIsTheSame") // test
    public Integer conuntHowManyProductIsTheSame(@RequestBody ProductSold productSold){
        return service.conuntHowManyProductIsTheSame(productSold);
    }

//    {
//        "id": 23,
//            "shop": {
//        "id": 1,
//                "name": "Warszawa",
//                "city": "Extra Pamiątki",
//                "street": "68",
//                "number": "Wodzickiego"
//    },
//        "price": 87,
//            "nazwa": "pocztówka"
//    }

    @GetMapping("/countHowMuchProductCostMore/{x}") // test
    public Integer countHowMuchProductCostMore(@PathVariable Double x) {
        return service.countHowMuchProductCostMore(x);
    }
}
