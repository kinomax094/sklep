package com.git.kinomax094.controller;

import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import com.git.kinomax094.repository.SoldProductRepository;
import com.git.kinomax094.service.ProductSoldService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerSoldProductTest {

    @InjectMocks
    private ControllerSoldProduct controller;

    @Mock
    ProductSoldService service;


    @Test
    public void addNewObjectByBody() {
        ProductSold x = new ProductSold();
        Mockito.when(service.addNewObjectByBody(x)).thenReturn(x);
        Assert.assertEquals(controller.addNewObjectByBody(new ProductSold()), x);
    }

    @Test
    public void addNewObjectByPath() throws NotFoundException {
        ProductSold x = new ProductSold();
        when(service.addNewObjectByPath("T", null, null, null)).thenReturn(x);
        Assert.assertEquals(controller.addNewObjectByPath("T",null,null,null), x);
    }

    @Test
    public void deleteObjectById() throws NotFoundException {
        ProductSold x = new ProductSold();
        when(service.deleteObjectById(1)).thenReturn(x);
        Assert.assertEquals(controller.deleteObjectById(1), x);
    }

    @Test
    public void findById() throws NotFoundException {
        ProductSold x = new ProductSold();
        when(service.findById(1)).thenReturn(x);
        Assert.assertEquals(controller.findById(1), x);
    }

    @Test
    public void findByIdCustomer() throws NotFoundException {
        List<ProductSold> x = new ArrayList<>();
        x.add(new ProductSold());
        when(service.findByIdCustomer(1)).thenReturn(x);
        Assert.assertEquals(controller.findByIdCustomer(1),x);
    }

    @Test
    public void findByIdShop() throws NotFoundException {
        List<ProductSold> x = new ArrayList<>();
        x.add(new ProductSold());
        when(service.findByIdShop(1)).thenReturn(x);
        Assert.assertEquals(controller.findByIdShop(1), x);
    }

    @Test
    public void conuntHowManyProductIsTheSame() {

        Integer x = 6;
        when(service.conuntHowManyProductIsTheSame(new ProductSold())).thenReturn(x);
        Assert.assertEquals(controller.conuntHowManyProductIsTheSame(new ProductSold()), x);
    }

    @Test
    public void countHowMuchProductCostMore() {

        Integer x = 6;
        when(service.countHowMuchProductCostMore(200.0)).thenReturn(x);
        Assert.assertEquals(controller.countHowMuchProductCostMore(200.0), x);
    }
}