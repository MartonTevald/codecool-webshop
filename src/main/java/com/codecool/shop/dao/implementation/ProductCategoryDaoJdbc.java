package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        ProductCategory result;
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM prodcat WHERE prodcat.id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                result = new ProductCategory(resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("description"));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM prodcat WHERE prodcat.id = ?");
            stmt.setString(1, String.valueOf(id));
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> prodcatres = new ArrayList<>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM prodcat");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("description"));
                prodcatres.add(productCategory);
            }
            return prodcatres;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
