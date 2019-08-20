package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductCategoryDaoJdbc extends DatabaseConnection implements ProductCategoryDao {
    @Override
    public void add(ProductCategory category) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO prodcat (name, department ,description) VALUES (?,?,?)");
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getDepartment());
            stmt.setString(3, category.getDescription());
            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
