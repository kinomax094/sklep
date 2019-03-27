package com.git.kinomax094.controller;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Product;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.repository.ProductRepository;
import com.git.kinomax094.repository.ShopRepository;
import com.git.kinomax094.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/product")
public class ControllerProduct {


    @Autowired
    private ProductService service;

    @PostMapping("/addNewObjectByBody") // test
    public Product addNewObjectByBody(@RequestBody Product product){
        return service.addNewObjectByBody(product);
    }

//    {
//        "id": 140,
//            "shop": {
//        "id": 1,
//                "name": "Warszawa",
//                "city": "Extra Pamiątki",
//                "street": "68",
//                "number": "Wodzickiego"
//    },
//        "price": 23.33,
//            "nazwa": "ttee"
//    }



    @PostMapping("/addNewObjectByPath/{name}/{price}/{id_shop}/{id_costomer}") // test
    public  Product addNewObjectByPath(@PathVariable String name, @PathVariable Double price, @PathVariable Integer id_shop) throws NotFoundException {
        return service.addNewObjectByPath(name, price, id_shop);
    }

    @DeleteMapping("/deleteObjectById/{id}") // test
    public  Product deleteObjectById(@PathVariable Integer id) throws NotFoundException {
        return  service.deleteObjectById(id);
    }

    @GetMapping("/findById/{id}") // test
    public  Product findById(@PathVariable Integer id) throws NotFoundException {
        return  service.findById(id);
    }

    @GetMapping("/findProductsByIdShop/{id}") // test
    public List<Product> findByIdShop(@PathVariable Integer id) throws NotFoundException {
        return service.findByIdShop(id);
    }

    @GetMapping("/conuntHowManyProductIsTheSame") //test
    public Integer conuntHowManyProductIsTheSame(@RequestBody Product product){
        return service.conuntHowManyProductIsTheSame(product);
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

    @GetMapping("/countHowMuchProductCostMore/{x}") //test
    public Integer countHowMuchProductCostMore(@PathVariable Double x) {
        return service.countHowMuchProductCostMore(x);
    }
}
