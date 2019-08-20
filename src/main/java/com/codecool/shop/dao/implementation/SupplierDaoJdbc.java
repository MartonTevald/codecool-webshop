package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDaoJdbc extends DatabaseConnection implements SupplierDao {
    @Override
    public void add(Supplier supplier) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO supplier (name, description)VALUES (?,?)");
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getDescription());
            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Supplier find(int id) {

        Supplier result;
        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                    "SELECT * FROM supplier WHERE supplier.id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                result = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
