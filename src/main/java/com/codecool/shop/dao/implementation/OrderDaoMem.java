package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.HashMap;

public class OrderDaoMem implements OrderDao {

    private HashMap<Product, Integer> data;
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
        /// TODO: 2019. 08. 07.  

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {
        /// TODO: 2019. 08. 07.  

    }
}
