package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDaoJdbcTest {

    @AfterEach
    void removeAll() {
        String query = "DELETE FROM supplier";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    SupplierDao supplierDao = new SupplierDaoJdbc();
    Supplier test1 = new Supplier(1, "Lenovo", "Hardware");
    Supplier test2 = new Supplier(1, "Apple", "Hardware");

    @Test
    void add() {
        SupplierDao.add(test1);
        assertEquals(test1.getName(), supplierDao.find(1).getName());

    }

    @Test
    void find() {
        SupplierDao.add(test1);
        assertEquals(test1.getName(), supplierDao.find(1).getName());
    }

    @Test
    void remove() {
        SupplierDao.add(test1);
        SupplierDao.add(test2);
        supplierDao.remove(2);
        assertEquals(1, supplierDao.getAll().size());
    }

    @Test
    void getAll() {
        SupplierDao.add(test1);
        SupplierDao.add(test2);
        assertEquals(2, supplierDao.getAll().size());
    }
}