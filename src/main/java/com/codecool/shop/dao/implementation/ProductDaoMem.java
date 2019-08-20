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
import java.util.stream.Collectors;

public class ProductDaoMem extends DatabaseConnection implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

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

//
//        product.setId(data.size() + 1);
//        data.add(product);
    }

    public int getSupplierId(String name) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT id FROM supplier WHERE supplier.name = " + name + "");
            ResultSet result = stmt.executeQuery();
            return Integer.parseInt(String.valueOf(result));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public int getProdCatId(String name) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT id FROM prodcat WHERE prodcat.name = " + name + "");
            ResultSet result = stmt.executeQuery();
            return Integer.parseInt(String.valueOf(result));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
