package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {

    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        data.add(order);

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return data;
    }

    @Override
    public void remove(int id) {
        /// TODO: 2019. 08. 07.  

    }
}
