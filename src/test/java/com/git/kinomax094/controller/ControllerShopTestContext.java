package com.git.kinomax094.controller;

import com.git.kinomax094.TestContext;
import com.git.kinomax094.exceptions.NotFoundException;
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

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/insert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/delete.sql")})
public class ControllerShopTestContext {

    @Autowired
    private ControllerShop controllerShop;

    @Test
    public void find1shop() throws NotFoundException {
        Shop shop = new Shop("Extra Pamiątki", "Warszawa", "Wodzickiego", "68");
        assertEquals(shop, controllerShop.findById(1));
    }

    @Test(expected = NotFoundException.class)
    public void find666shop() throws NotFoundException {
        controllerShop.findById(666);
    }

    @Test(expected = NotFoundException.class)
    public void delete1Shop() throws NotFoundException {
        controllerShop.deleteObjectById(1);
        controllerShop.findById(1);
    }

    @Test
    public void cityWithBiggestNumberOfShop() {
        assertEquals("Kraków", controllerShop.cityWithBigestNumberOfShop());
    }

    @Test
    public void findCustomerWithAmountOfOrdersBigestThat200() {
        assertEquals(6, controllerShop.findCustomerWithAmountOfOrdersBigestThatArg(200).size());
    }

    @Test
    public void shopWithBigestpPofit() throws NotFoundException {
        Shop shop = new Shop("Extra Pamiątki", "Warszawa", "Wodzickiego", "68");
        assertEquals(shop, controllerShop.shopWithBigestpPofit());
    }

}
