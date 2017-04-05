package com.globallogic.codingdojo.testexamples.model;

/**
 * @author Julio Kun
 */

public class ProductMockFactory {

    public Product getFirstProduct() {
        return new Product("First product", "tech", 25f);
    }

    public Product getSecondProduct() {
        return new Product("Second product", "tech", 25f);
    }

    public Product getThirdProduct() {
        return new Product("Third product", "food", 5.5f);
    }

    public Product getFourthProduct() {
        return new Product("Third product", "food", 2.84f);
    }

    public Product getFIfthProduct() {
        return new Product("Third product", "food", 59.9f);
    }
}
