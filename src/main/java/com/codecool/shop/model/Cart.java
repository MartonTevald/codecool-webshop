package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private static final Cart INSTANCE = new Cart();


    private Cart() {
    }

    public static Cart getInstance() {
        return INSTANCE;
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    private HashMap<Product, Integer> cart = new HashMap<>();

    public float getSumOfPrice() {
        float sum = 0;
        for (Map.Entry<Product, Integer> map : cart.entrySet()) {
            float key = map.getKey().getDefaultPrice();
            Integer value = map.getValue();
            sum += (key * value);
        }
        return sum;
    }

    public int getNumberOfProducts() {
        int sum = 0;
        for (Product key : cart.keySet()) {
            sum += cart.get(key);
        }
        return sum;
    }

    public void addToCart(Product product) {
        if (cart.containsKey(product)) {
            Integer actual = cart.get(product) + 1;
            System.out.println(actual);
            cart.put(product, actual);
        } else {
            cart.put(product, 1);
        }
    }

    void removeFromCart(Product product) {
        cart.remove(product);
    }

    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        for (Product prod : cart.keySet()) {
            list.add(prod);
        }
        return list;


    }

}