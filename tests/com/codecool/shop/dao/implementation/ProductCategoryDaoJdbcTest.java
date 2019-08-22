package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest extends DatabaseConnection {

    private ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJdbc();

    ProductCategory cat1 = new ProductCategory(1,"Tablet","Hardware","very nice");
    ProductCategory cat2 = new ProductCategory(2,"Laptop","Hardware","very nice");

    @Test
    void add() {
        productCategoryDataStore.add(cat1);
        productCategoryDataStore.getAll();
        assertEquals(1,productCategoryDataStore.getAll().size());
    }

    @Test
    void find() {
    }

    @Test
    void remove() {
    }

    @Test
    void getAll() {
    }
}