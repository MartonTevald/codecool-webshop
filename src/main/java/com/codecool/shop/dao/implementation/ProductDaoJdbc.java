package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoJdbc extends DatabaseConnection implements ProductDao {


    @Override
    public void add(Product product) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO product (name, description,price,supplier_id,prodcat_id) VALUES (?,?,?,?,?)");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getDefaultPrice());
            stmt.setInt(4, getSupplierId(product.getSupplier().getName()));
            stmt.setInt(5, getProdCatId(product.getProductCategory().getName()));

            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public int getSupplierId(String name) {
        String result = null;
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT supplier.id FROM supplier WHERE supplier.name = ?");
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

        try {
            PreparedStatement stmt = getConnection().prepareStatement("" +
                    "SELECT prodcat.id FROM prodcat WHERE name = ?");
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
        return null;
    }


    @Override
    public void remove(int id) {
    }

    @Override
    public List<Product> getAll() {
        return null;
    }


    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
