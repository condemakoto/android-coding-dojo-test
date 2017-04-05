package com.globallogic.codingdojo.testexamples;

import com.globallogic.codingdojo.testexamples.model.Product;
import com.globallogic.codingdojo.testexamples.model.ProductMockFactory;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author Julio Kun
 */

public class TestWithMockito {
    private ProductMockFactory productsFactory;

    @Before
    public void setUp() {
        productsFactory = new ProductMockFactory();
    }

    private ArrayList<Product> getListOfProductsToTest() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(productsFactory.getFirstProduct());
        products.add(productsFactory.getSecondProduct());
        products.add(productsFactory.getThirdProduct());
        products.add(productsFactory.getFourthProduct());
        products.add(productsFactory.getFIfthProduct());

        return products;
    }

    @Test
    public void testProductsInteractorWithoutMockito() {
        //Without Mockito I have to do a full implementation of ProductsService
        ProductsInteractor productsInteractor = new ProductsInteractor(new ProductsService() {
            @Override
            public Product getProduct(int id) {
                return null;
            }

            @Override
            public ArrayList<Product> getAllProducts() {
                return getListOfProductsToTest();
            }

            @Override
            public boolean deleteProduct(Product product) {
                return false;
            }

            @Override
            public boolean updateProduct(Product product) {
                return false;
            }
        });

        ArrayList<Product> productsSorted = productsInteractor.getProductsSortedByPrice();

        assertNotNull(productsSorted);

        float currentPrice = productsSorted.get(0).getPrice();
        for (int i = 1; i < productsSorted.size(); i++) {
            Product product = productsSorted.get(i);
            assertTrue(product.getPrice() > currentPrice);
        }
    }

    @Test
    public void testProductsInteractorWithMockito() {
        ProductsService productsService = Mockito.mock(ProductsService.class);
        Mockito.when(productsService.getAllProducts()).thenReturn(getListOfProductsToTest());
        ProductsInteractor productsInteractor = new ProductsInteractor(productsService);

        ArrayList<Product> productsSorted = productsInteractor.getProductsSortedByPrice();

        assertNotNull(productsSorted);

        float currentPrice = productsSorted.get(0).getPrice();
        for (int i = 1; i < productsSorted.size(); i++) {
            Product product = productsSorted.get(i);
            assertTrue(product.getPrice() > currentPrice);
        }

        //buts that's not all, with Mockito I can do even more checks, for instance I can see how many times a method was invoked
        //In this case I'm going to check that the method getAllProducts() of ProductsService
        //was called just once.
        Mockito.verify(productsService, Mockito.times(1)).getAllProducts();

    }

    @Test
    public void testProductsInteractorWithMockitoToSpyInstance() {
        //Without Mockito I have to do a full implementation of ProductsService
        ProductsService productsService = new ProductsService() {
            @Override
            public Product getProduct(int id) {
                return null;
            }

            @Override
            public ArrayList<Product> getAllProducts() {
                return getListOfProductsToTest();
            }

            @Override
            public boolean deleteProduct(Product product) {
                return false;
            }

            @Override
            public boolean updateProduct(Product product) {
                return false;
            }
        };
        productsService = Mockito.spy(productsService);
        ProductsInteractor productsInteractor = new ProductsInteractor(productsService);

        ArrayList<Product> productsSorted = productsInteractor.getProductsSortedByPrice();

        assertNotNull(productsSorted);

        float currentPrice = productsSorted.get(0).getPrice();
        for (int i = 1; i < productsSorted.size(); i++) {
            Product product = productsSorted.get(i);
            assertTrue(product.getPrice() > currentPrice);
        }

        //add additional check with Mockito
        Mockito.verify(productsService, Mockito.times(1)).getAllProducts();
    }

    /**
     * Class to test. We wanna know if order by price works fine for a given set
     * of data.
     */
    public static class ProductsInteractor {

        private ProductsService productsService;

        public ProductsInteractor(ProductsService productsService) {
            this.productsService = productsService;
        }

        public ArrayList<Product> getProductsSortedByPrice() {
            ArrayList<Product> products = productsService.getAllProducts();

            if (products != null) {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product first, Product second) {
                        return Float.compare(first.getPrice(), second.getPrice());
                    }
                });
            }

            return products;
        }
    }

    public interface ProductsService {
        Product getProduct(int id);

        ArrayList<Product> getAllProducts();

        boolean deleteProduct(Product product);

        boolean updateProduct(Product product);
    }
}
