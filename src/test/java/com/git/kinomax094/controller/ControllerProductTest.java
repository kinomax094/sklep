package com.git.kinomax094.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Product;
import com.git.kinomax094.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerProductTest {

    @InjectMocks
    ControllerProduct controller;

    @Mock
    ProductService service;


    @Test
    public void addNewObjectByBody() {
        Product x = new Product();
        when(service.addNewObjectByBody(x)).thenReturn(x);
        Assert.assertEquals(controller.addNewObjectByBody(new Product()), x);
    }

    @Test
    public void addNewObjectByPath() throws NotFoundException {

        Product x = new Product();
        when(service.addNewObjectByPath("t",23.3,1)).thenReturn(x);
        Assert.assertEquals(controller.addNewObjectByPath("t",23.3,1), x);
    }

    @Test
    public void deleteObjectById() throws NotFoundException {

        Product x = new Product();
        when(service.deleteObjectById(1)).thenReturn(x);
        Assert.assertEquals(controller.deleteObjectById(1), x);
    }

    @Test
    public void findById() throws NotFoundException {

        Product x = new Product();
        when(service.findById(1)).thenReturn(x);
        Assert.assertEquals(controller.findById(1), x);
    }

    @Test
    public void findByIdShop() throws NotFoundException {

        List<Product> x = new ArrayList<>();
        x.add(new Product());
        when(service.findByIdShop(1)).thenReturn(x);
        Assert.assertEquals(controller.findByIdShop(1), x);
    }

    @Test
    public void conuntHowManyProductIsTheSame() {

        Integer x = 5;
        when(service.conuntHowManyProductIsTheSame(new Product())).thenReturn(x);
        Assert.assertEquals(controller.conuntHowManyProductIsTheSame(new Product()), x);
    }

    @Test
    public void countHowMuchProductCostMore() {
        Integer x = 5;
        when(service.countHowMuchProductCostMore(200.0)).thenReturn(x);
        Assert.assertEquals(controller.countHowMuchProductCostMore(200.0), x);
    }
}