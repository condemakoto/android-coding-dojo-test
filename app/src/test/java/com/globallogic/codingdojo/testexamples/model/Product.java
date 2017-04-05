package com.globallogic.codingdojo.testexamples.model;

import com.google.common.base.Objects;

/**
 * @author Julio Kun
 */

public class Product {

    private String name;
    private String category;
    private float price;

    public Product(String name, String category, float price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Product po = (Product) o;
        return Objects.equal(name, po.name)
                && Objects.equal(category, po.category)
                && Objects.equal(price, price);
    }
}
