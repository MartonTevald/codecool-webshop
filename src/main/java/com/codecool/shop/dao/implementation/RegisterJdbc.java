package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DatabaseConnection;
import com.codecool.shop.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterJdbc extends DatabaseConnection {

    public  void addUser(User user){
        try {
            PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO user_information(fullname, username, email, password) VALUES (?,?,?,?)");
            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
