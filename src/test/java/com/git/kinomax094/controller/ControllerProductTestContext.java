package com.git.kinomax094.controller;

import com.git.kinomax094.TestContext;
import com.git.kinomax094.exceptions.NotFoundException;
import com.git.kinomax094.model.Product;
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

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/insert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/delete.sql")})
public class ControllerProductTestContext {

    @Autowired
    private ControllerProduct controllerProduct;


    @Test
    public void find2Product() throws NotFoundException {

        Shop shop = new Shop("Extra Pamiątki", "Warszawa", "Wodzickiego", "68");
        Product product = new Product(shop, "maskotka", 12.0);

        assertEquals(product, controllerProduct.findById(2));
    }

    @Test
    public void countHowMuchProductCostMoreThen5() {
        assertEquals(Optional.of(135).get(), controllerProduct.countHowMuchProductCostMore(5.0));
    }

    @Test
    public void findProductsByIdShop2() throws NotFoundException {
        assertEquals(30, controllerProduct.findByIdShop(2).size());
    }
}
