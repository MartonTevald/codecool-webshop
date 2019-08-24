package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.database.DatabaseConnection;
import com.codecool.shop.database.DatabaseLive;
import com.codecool.shop.model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoJdbc extends DatabaseConnection implements OrderDao {


    @Override
    public void add(Order order) {

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
