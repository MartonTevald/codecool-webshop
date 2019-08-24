package com.codecool.shop.dao;


import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User find(String username);
    User find(int id);

    void remove(String username);

    List<User> getAll();

    List<User> getBy(Order id);

    String getHashedPwd(String username);

    int getUserId(String username);

}
