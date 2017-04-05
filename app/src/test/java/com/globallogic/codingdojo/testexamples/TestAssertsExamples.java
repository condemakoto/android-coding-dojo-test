package com.globallogic.codingdojo.testexamples;

import com.globallogic.codingdojo.testexamples.model.Adder;
import com.globallogic.codingdojo.testexamples.model.Product;
import com.globallogic.codingdojo.testexamples.model.ProductMockFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Julio Kun
 */

public class TestAssertsExamples {


    @Test
    public void testSumWithAssertTrue() {
        Adder adder = new Adder();
        Assert.assertTrue(adder.sum(2, 2) == 5);
    }

    @Test
    public void testSumWithAssertEquals() {
        Adder adder = new Adder();
        Assert.assertEquals(5, adder.sum(2, 2));
    }

    @Test
    public void testProductsSameProduct() {
        ProductMockFactory factory = new ProductMockFactory();

        Product firstProduct = factory.getFirstProduct();
        Assert.assertNotNull(firstProduct);

        Product secondProduct = factory.getFirstProduct();
        Assert.assertNotNull(secondProduct);

        Assert.assertEquals(firstProduct, secondProduct);
    }


    @Test
    public void testProducts() {
        ProductMockFactory factory = new ProductMockFactory();

        Product firstProduct = factory.getFirstProduct();
        Assert.assertNotNull(firstProduct);

        Product secondProduct = factory.getSecondProduct();
        Assert.assertNotNull(secondProduct);

        Assert.assertEquals(firstProduct, secondProduct);
    }

    @Test
    public void testProducts2() {
        ProductMockFactory factory = new ProductMockFactory();

        Product firstProduct = factory.getFirstProduct();
        Assert.assertNotNull(firstProduct);

        Product secondProduct = factory.getSecondProduct();
        Assert.assertNotNull(secondProduct);

        Assert.assertEquals(firstProduct.getCategory(), secondProduct.getCategory());
        Assert.assertEquals(firstProduct.getName(), secondProduct.getName());
        Assert.assertEquals(firstProduct.getPrice(), secondProduct.getPrice(), 0);
    }


}
