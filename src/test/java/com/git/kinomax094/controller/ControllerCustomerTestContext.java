package com.git.kinomax094.controller;

import com.git.kinomax094.TestContext;
import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Customer;
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

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/insert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/delete.sql")})
public class ControllerCustomerTestContext {

    @Autowired
    private ControllerCustomer controllerCustomer;

    @Test
    public void find2Customer() throws NotFoundException {
        Shop shop = new Shop("Extra Pamiątki", "Warszawa", "Wodzickiego", "68");
        Customer customer = new Customer(shop, "Maria", "Berdysowa", "Warszawa", "Wyzwolenia", "80");
        Assert.assertEquals(customer, controllerCustomer.findById(2));
    }


    @Test(expected = NotFoundException.class)
    public void delete2Customer() throws NotFoundException {
        controllerCustomer.deleteObjectById(2);
        controllerCustomer.findById(2);
    }

    @Test
    public void sumOfSoldProductId2() throws NotFoundException {
        Assert.assertEquals(java.util.Optional.of(240.0).get(), controllerCustomer.sumOfSoldProduct(2));
    }

}
