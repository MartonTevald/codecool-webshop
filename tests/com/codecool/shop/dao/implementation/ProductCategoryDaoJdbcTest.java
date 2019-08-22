package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest {

    ProductCategory cat1 = new ProductCategory(1, "Tablet", "Hardware", "very nice");
    ProductCategory cat2 = new ProductCategory(2, "Laptop", "Hardware", "very nice");
    TestUtil testUtil = new TestUtil();
    ProductCategoryDao prodCatDatabase = new ProductCategoryDaoJdbc();


    @AfterEach
    void setup() {
        testUtil.removeAll(testUtil.product);
        testUtil.removeAll(testUtil.prodcat);
        testUtil.removeAll(testUtil.supplier);
        testUtil.create(testUtil.queryCategory);
        testUtil.create(testUtil.querySupplier);
        testUtil.create(testUtil.queryProduct);
    }

    @Test
    void add() {
        prodCatDatabase.add(cat1);
        assertEquals(1,
                prodCatDatabase.getAll().size());
    }

    @Test
    void addMore() {
        prodCatDatabase.add(cat1);
        prodCatDatabase.add(cat2);
        assertEquals(2, prodCatDatabase.getAll().size());
    }


    @Test
    void find() {
        prodCatDatabase.add(cat1);
        assertEquals(cat1.getName(), prodCatDatabase.find(1).getName());
    }

    @Test
    void remove() {
        prodCatDatabase.add(cat1);
        prodCatDatabase.add(cat2);
        prodCatDatabase.remove(1);
        assertEquals(1, prodCatDatabase.getAll().size());
    }

    @Test
    void removeAllCategory() {
        prodCatDatabase.add(cat1);
        prodCatDatabase.add(cat2);
        prodCatDatabase.remove(1);
        prodCatDatabase.remove(2);
        assertEquals(0, prodCatDatabase.getAll().size());
    }

    @Test
    void getAll() {
        prodCatDatabase.add(cat1);
        prodCatDatabase.add(cat2);
        assertEquals(2, prodCatDatabase.getAll().size());
    }
}