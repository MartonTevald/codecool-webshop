package com.codecool.shop.dao.implementation;

import com.codecool.shop.database.DatabaseConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc extends DatabaseConnection implements ProductDao {

    private ProductCategoryDaoJdbc prodCatJdbc = new ProductCategoryDaoJdbc();
    private SupplierDaoJdbc suppJdbc = new SupplierDaoJdbc();


    @Override
    public void add(Product product) {
        String query = "INSERT INTO product (name, description,price,supplier_id,prodcat_id) " +
                "VALUES (?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getDefaultPrice());
            statement.setInt(4, getSupplierId(product.getSupplier().getName()));
            statement.setInt(5, getProdCatId(product.getProductCategory().getName()));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getSupplierId(String name) {
        String result = null;
        String query = "SELECT supplier.id FROM supplier " +
                "WHERE supplier.name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet res = statement.executeQuery();

            if (res.next())
                result = res.getString(1);
            return Integer.parseInt(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public int getProdCatId(String name) {
        String result = null;
        String query = "SELECT prodcat.id FROM prodcat WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet res = statement.executeQuery();

            if (res.next())
                result = res.getString(1);
            return Integer.parseInt(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public Product find(int id) {
        Product result;
        String query = "SELECT * FROM product " +
                "WHERE product.id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        "USD",
                        resultSet.getString("description"),
                        prodCatJdbc.find(resultSet.getInt("prodcat_id")),
                        suppJdbc.find(resultSet.getInt("supplier_id")));

                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product " +
                "WHERE product.id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product";
        List<Product> products = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product;
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        "USD",
                        resultSet.getString("description"),
                        prodCatJdbc.find(resultSet.getInt("prodcat_id")),
                        suppJdbc.find(resultSet.getInt("supplier_id")));

                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product " +
                "WHERE product.supplier_id = ?";
        int supplierId = getSupplierId(supplier.getName());
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product;
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        "USD",
                        resultSet.getString("description"),
                        prodCatJdbc.find(resultSet.getInt("prodcat_id")),
                        suppJdbc.find(resultSet.getInt("supplier_id")));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product " +
                "WHERE product.prodcat_id = ?";
        int supId = getProdCatId(productCategory.getName());
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, supId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product;
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        "USD",
                        resultSet.getString("description"),
                        prodCatJdbc.find(resultSet.getInt("prodcat_id")),
                        suppJdbc.find(resultSet.getInt("supplier_id")));
                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
