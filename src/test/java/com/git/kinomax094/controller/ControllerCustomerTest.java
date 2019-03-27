package com.git.kinomax094.controller;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerCustomerTest {

    @InjectMocks
    private ControllerCustomer controller;

    @Mock
    CustomerService service;



    @Test
    public void addNewObjectByBody() {

        Customer x = new Customer();
        when(service.addNewObjectByBody(x)).thenReturn(x);
        Assert.assertEquals(controller.addNewObjectByBody(new Customer()), x);
    }

    @Test
    public void addNewObjectByPath() throws NotFoundException {

        Customer x = new Customer(new Shop(),"t","t", "t", "t", "t");
        when(service.addNewObjectByPath(1,"t","t", null, null, null)).thenReturn(x);
        Assert.assertEquals(controller.addNewObjectByPath("t", "t",1), x);
    }

    @Test
    public void deleteObjectById() throws NotFoundException {
        Customer x = new Customer(new Shop(),"t","t", "t", "t", "t");
        when(service.deleteObjectById(1)).thenReturn(x);
        Assert.assertEquals(controller.deleteObjectById(1), x);
    }

    @Test
    public void findById() throws NotFoundException {
        Customer x = new Customer(new Shop(),"t","t", "t", "t", "t");
        when(service.findById(1)).thenReturn(x);
        Assert.assertEquals(controller.findById(1), x);
    }

    @Test
    public void sumOfSoldProduct() throws NotFoundException {
        when(service.sumOfSoldProduct(1)).thenReturn(2000.0);
        Assert.assertEquals(controller.sumOfSoldProduct(1), new Double(2000));
    }

    @Test
    public void returnTheExpensiveProductFromSolded() throws NotFoundException {

        ProductSold x = new ProductSold();
        when(service.returnTheExpensiveProduct(1)).thenReturn(x);
        Assert.assertEquals(controller.returnTheExpensiveProductFromSolded(1),x);
    }

    @Test
    public void findAllCustomerBuyMoreThat() throws NotFoundException {
        List<Customer> list = new ArrayList<>();
        list.add(new Customer());
        when(service.findAllCustomerBuyMoreThat(200.0)).thenReturn(list);
        Assert.assertEquals(controller.findAllCustomerBuyMoreThat(200.0), list);
    }

    @Test
    public void findAllCustomerBuyLessThat() throws NotFoundException {
        List<Customer> list = new ArrayList<>();
        list.add(new Customer());
        when(service.findAllCustomerBuyLessThat(200.0)).thenReturn(list);
        Assert.assertEquals(controller.findAllCustomerBuyLessThat(200.0), list);
    }
}