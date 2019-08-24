package com.codecool.shop.user.implentation;


import com.codecool.shop.user.implentation.UserDao;
import com.codecool.shop.database.DatabaseConnection;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc extends DatabaseConnection implements UserDao {

    @Override
    public void add(User user) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("" +
                    "INSERT INTO user_details(fullname, username, email, password) VALUES (?,?,?,?)");
            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User find(String username) {
        User result;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("" +
                    "SELECT * FROM user_details WHERE username = ?");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                result = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User find(int id) {
        User result;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("" +
                    "SELECT * FROM user_details WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                result = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(String username) {
        String query = "DELETE FROM user_details " +
                "WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("" +
                    "SELECT * FROM user_details");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("fullname"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getBy(Order id) {

        return null;
    }

    @Override
    public String getHashedPwd(String username) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("" +
                    "SELECT password FROM user_details WHERE username = ?");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getUserId(String username) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement("" +
                    "SELECT id FROM user_details WHERE username = ?");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
