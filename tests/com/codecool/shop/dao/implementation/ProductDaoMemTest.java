package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoMemTest {

    private ProductDaoMem prod;

    @BeforeEach
    void setup(){
        prod = ProductDaoMem.getInstance();
        prod.getAll().clear();
    }

    ProductCategory cat1 = new ProductCategory(1, "Tablet", "Hardware", "very nice");
    ProductCategory cat2 = new ProductCategory(2, "Laptop", "Hardware", "very nice");
    Supplier sup1 = new Supplier(1, "sup1", "supDesc1");
    Supplier sup2 = new Supplier(2, "sup2", "supDesc2");
    Product prod1 = new Product(1, "name1", 1, "ISK", "desc1", cat1, sup1);
    Product prod2 = new Product(2, "name2", 2, "ISK", "desc2", cat2, sup2);



    @Test
    void testAddSingleProduct() {
        prod.add(prod1);
        assertEquals(1, prod.getAll().size());
    }

    @Test
    void testFindSingleProduct() {
        prod.add(prod1);
        assertEquals(prod1, prod.find(1));
    }

    @Test
    void testRemoveSingleProduct() {
        prod.add(prod1);
        prod.add(prod2);
        prod.remove(1);
        assertEquals(1, prod.getAll().size());
    }

    @Test
    void testGetAllProducts() {
        prod.add(prod1);
        prod.add(prod2);
        assertEquals(2, prod.getAll().size());
    }

    @Test
    void testGetBySupplier() {
        prod.add(prod1);
        prod.add(prod2);
        assertEquals(Arrays.asList(prod1), prod.getBy(sup1));
    }

    @Test
    void testGetByProductCategory() {
        prod.add(prod1);
        prod.add(prod2);
        assertEquals(Arrays.asList(prod1), prod.getBy(cat1));
    }
}