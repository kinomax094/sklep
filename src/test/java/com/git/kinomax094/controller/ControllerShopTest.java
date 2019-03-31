package com.git.kinomax094.controller;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.Product;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.service.ShopService;
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
public class ControllerShopTest {

    @InjectMocks
    ControllerShop controller;

    @Mock
    ShopService service;


    @Test
    public void addNewObjectByBody() {

        Shop x = new Shop();
        when(service.addNewObjectByBody(x)).thenReturn(x);
        Assert.assertEquals(controller.addNewObjectByBody(new Shop()), x);
    }

    @Test
    public void addNewObjectByPath() throws NotFoundException {
        Shop x = new Shop();
        when(service.addNewObjectByPath("T",null,null,null)).thenReturn(x);
        Assert.assertEquals(controller.addNewObjectByPath("T",null,null,null), x);
    }

    @Test
    public void deleteObjectById() throws NotFoundException {
        Shop x = new Shop();
        when(service.deleteObjectById(1)).thenReturn(x);
        Assert.assertEquals(controller.deleteObjectById(1), x);
    }

    @Test
    public void findById() throws NotFoundException {
        Shop x = new Shop();
        when(service.findById(1)).thenReturn(x);
        Assert.assertEquals(controller.findById(1), x);
    }

    @Test
    public void shopWithBigestpPofit() throws NotFoundException {

        Shop x = new Shop();
        when(service.shopWithBigestpPofit()).thenReturn(x);
        Assert.assertEquals(controller.shopWithBigestpPofit(), x);
    }

    @Test
    public void cityWithBigestNumberOfShop() {

        String x = "w";
        when(service.cityWithBigestNumberOfShop()).thenReturn(x);
        Assert.assertEquals(controller.cityWithBigestNumberOfShop(),x);
    }

    @Test
    public void findCustomerWithAmountOfOrdersBigestThatArg() {

        List<Customer> x = new ArrayList<>();
        x.add(new Customer());
        when(service.findCustomerWithAmountOfOrdersBigestThatArg(200.0)).thenReturn(x);
        Assert.assertEquals(controller.findCustomerWithAmountOfOrdersBigestThatArg(200.0), x);
    }

    @Test
    public void findCustomerWithBigestSoldProduct() {

        String x = "t";
        when(service.findCustomerWithBigestSoldProduct()).thenReturn(x);
        Assert.assertEquals(controller.findCustomerWithBigestSoldProduct(), x);
    }
}