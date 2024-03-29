package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoMemTest {

    ProductCategoryDaoMem prodCat;
    TestUtil testUtil = new TestUtil();

    @BeforeEach
    void setup() {
        testUtil.setupDatabase();

        prodCat = ProductCategoryDaoMem.getInstance();
        prodCat.getAll().clear();
    }

    ProductCategory cat1 = new ProductCategory(1, "Tablet", "Hardware", "very nice");
    ProductCategory cat2 = new ProductCategory(2, "Laptop", "Hardware", "very nice");


    @Test
    void add() {
        prodCat.add(cat1);
        assertEquals(1, prodCat.getAll().size());
    }

    @Test
    void findCatById() {
        prodCat.add(cat1);
        prodCat.add(cat2);
        assertEquals(cat1, prodCat.find(1));
    }

    @Test
    void findCatByName() {
        prodCat.add(cat1);
        assertEquals(cat1, prodCat.find("Tablet"));
    }

    @Test
    void remove() {
        prodCat.add(cat1);
        prodCat.remove(1);
        assertEquals(0, prodCat.getAll().size());
    }

    @Test
    void getAll() {
        prodCat.add(cat1);
        prodCat.add(cat2);
        assertEquals(2, prodCat.getAll().size());
    }
}