package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUtil {
    String prodcat = "prodcat";
    String product = "product";
    String supplier = "supplier";
    String querySupplier = "CREATE TABLE supplier(\n" +
            "    id  SERIAL PRIMARY KEY ,\n" +
            "    name VARCHAR(50) UNIQUE ,\n" +
            "    department varchar(50),\n" +
            "    description varchar(200));";
    String queryCategory = "CREATE TABLE prodCat(\n" +
            "    id SERIAL PRIMARY KEY ,\n" +
            "    name VARCHAR(50) UNIQUE ,\n" +
            "    department varchar(50),\n" +
            "    description varchar(500));";
    String queryProduct = "CREATE TABLE product(\n" +
            "    id SERIAL PRIMARY KEY,\n" +
            "    name varchar(150) UNIQUE ,\n" +
            "    description varchar(500),\n" +
            "    price float,\n" +
            "    supplier_id int  REFERENCES supplier(id),\n" +
            "    prodCat_id int REFERENCES prodCat(id));";


    void removeAll(String table){
        String query = "DROP TABLE " + table + ";";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void create(String query){
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
