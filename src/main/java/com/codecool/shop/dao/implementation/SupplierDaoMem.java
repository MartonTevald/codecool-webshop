package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem extends DatabaseConnection implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }


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
//        supplier.setId(data.size() + 1);
//        data.add(supplier);
    }

//    public Supplier find(String name) {
//        for (Supplier sup : data) {
//            if (name.equals(sup.getName())) {
//                return sup;
//            }
//        }
//        return null;
//    }

    @Override
    public Supplier find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() {
        return data;
    }
}
