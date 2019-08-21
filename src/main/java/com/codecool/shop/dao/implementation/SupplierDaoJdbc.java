package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc extends DatabaseConnection implements SupplierDao {
    @Override
    public void add(Supplier supplier) {
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(
                "INSERT INTO supplier (name, description)" +
                        "VALUES (?,?)")) {
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Supplier find(int id) {

        Supplier result;
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(
                "SELECT * FROM supplier " +
                        "WHERE supplier.id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
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
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(
                "DELETE FROM supplier " +
                        "WHERE id = ?")) {
            statement.setString(1, String.valueOf(id));
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(
                "SELECT * FROM supplier")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
