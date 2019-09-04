package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.database.DatabaseConnection;

import com.codecool.shop.model.Order;

import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoJdbc extends DatabaseConnection {


    public void addOrder(int id, String status) {
        String query = "INSERT INTO \"order\" (user_id,status)" +
                "VALUES (?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, status);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isThereAnOpenOrder(String status, int id) {
        String query = "SELECT * FROM \"order\" " +
                "WHERE status = ? AND user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateOrderStatus(int id, String status, String actualStatus) {
        String query = "UPDATE \"order\"" +
                "SET status = ?" +
                "WHERE user_id = ? AND  status = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.setString(3, actualStatus);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void remove(int id) {
        //TODO

    }


    public List<Order> getAll() {
        //TODO
        return null;
    }
}
