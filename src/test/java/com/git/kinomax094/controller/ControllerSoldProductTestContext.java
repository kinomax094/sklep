package com.git.kinomax094.controller;

import com.git.kinomax094.TestContext;
import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
import com.git.kinomax094.model.ProductSold;
import com.git.kinomax094.model.Shop;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/insert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/delete.sql")})
public class ControllerSoldProductTestContext {

    @Autowired
    private ControllerSoldProduct controllerSoldProduct;

    @Test
    public void find2SoldProduct() throws NotFoundException {
        Shop shop = new Shop("Extra Pamiątki", "Warszawa", "Wodzickiego", "68");
        Customer customer = new Customer(shop, "Ewa", "Gnutek", "Poznan", "Kiedrzynska", "15");
        ProductSold productSold = new ProductSold(shop, customer, "pocztówka", 12.0);
        Assert.assertEquals(productSold, controllerSoldProduct.findById(2));
    }

    @Test(expected = NotFoundException.class)
    public void find37281SoldProduct() throws NotFoundException {
        controllerSoldProduct.findById(37281);
    }

    @Test(expected = NotFoundException.class)
    public void delete1SoldProduct() throws NotFoundException {
        controllerSoldProduct.deleteObjectById(1);
        controllerSoldProduct.findById(1);
    }

    @Test
    public void findProductsByCustomerId9() throws NotFoundException {
        Shop shop = new Shop("Extra Pamiątki", "Warszawa", "Wodzickiego", "68");
        Customer customer = new Customer(shop, "Ewa", "Gnutek", "Poznan", "Kiedrzynska", "15");
        ProductSold productSold = new ProductSold(shop, customer, "pocztówka", 12.0);
        Assert.assertTrue(controllerSoldProduct.findByIdCustomer(9).contains(productSold));
    }

    @Test
    public void countHowMuchProductCostMoreThen20() {
        Assert.assertEquals(Optional.of(45).get(), controllerSoldProduct.countHowMuchProductCostMore(20.0));
    }
}
