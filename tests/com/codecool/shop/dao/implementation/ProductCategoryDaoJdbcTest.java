package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest extends DatabaseConnection {

    @AfterEach
    void removeAll() {
        String query = "DELETE  FROM prodcat ";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ProductCategoryDao prodCatDatabase = new ProductCategoryDaoJdbc();
    ProductCategory cat1 = new ProductCategory(1, "Tablet", "Hardware", "very nice");
    ProductCategory cat2 = new ProductCategory(2, "Laptop", "Hardware", "very nice");

    @Test
    void add() {
        prodCatDatabase.add(cat1);
        assertEquals(1,
                prodCatDatabase.getAll().size());
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
    void getAll() {
        prodCatDatabase.add(cat1);
        prodCatDatabase.add(cat2);
        assertEquals(2, prodCatDatabase.getAll().size());
    }
}