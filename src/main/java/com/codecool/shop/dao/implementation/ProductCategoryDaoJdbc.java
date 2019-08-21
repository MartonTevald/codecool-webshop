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
        try (PreparedStatement statement = getConnection().prepareStatement(
                "INSERT INTO prodcat (name, department ,description) " +
                        "VALUES (?,?,?)")) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDepartment());
            statement.setString(3, category.getDescription());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id) {
        ProductCategory result;
        try (PreparedStatement statement = getConnection().prepareStatement(
                "SELECT * FROM prodcat " +
                        "WHERE prodcat.id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
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
        try (PreparedStatement statement = getConnection().prepareStatement(
                "DELETE FROM prodcat " +
                        "WHERE prodcat.id = ?")) {
            statement.setString(1, String.valueOf(id));
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> prodcatres = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(
                "SELECT * FROM prodcat")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                prodcatres.add(productCategory);
            }
            return prodcatres;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
