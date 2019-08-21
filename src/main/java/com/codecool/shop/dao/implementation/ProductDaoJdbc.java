package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

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
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "INSERT INTO product (name, description,price,supplier_id,prodcat_id) " +
                        "VALUES (?,?,?,?,?)")) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getDefaultPrice());
            stmt.setInt(4, getSupplierId(product.getSupplier().getName()));
            stmt.setInt(5, getProdCatId(product.getProductCategory().getName()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public int getSupplierId(String name) {
        String result = null;
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT supplier.id FROM supplier " +
                        "WHERE supplier.name = ?")) {
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();

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

        try (PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT prodcat.id FROM prodcat WHERE name = ?")) {
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();

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
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM product " +
                        "WHERE product.id = ?")) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
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
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "DELETE FROM product " +
                        "WHERE product.id = ?")) {
            stmt.setString(1, String.valueOf(id));
            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM product")) {
            ResultSet resultSet = stmt.executeQuery();
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

        int supplierId = getSupplierId(supplier.getName());
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM product " +
                        "WHERE product.supplier_id = ?")) {
            stmt.setInt(1, supplierId);
            ResultSet resultSet = stmt.executeQuery();
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

        int supId = getProdCatId(productCategory.getName());
        try (PreparedStatement stmt = getConnection().prepareStatement(
                "SELECT * FROM product " +
                        "WHERE product.prodcat_id = ?")) {
            stmt.setInt(1, supId);
            ResultSet resultSet = stmt.executeQuery();
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
