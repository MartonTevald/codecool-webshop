package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final Cart INSTANCE = new Cart();

    private Cart() {}

    public static Cart getInstance() {
        return INSTANCE;
    }

    public List<Product> getCart() {
        return cart;
    }

    private List<Product> cart = new ArrayList<>();

    void addToCart(Product product){
        cart.add(product);
    }

    void removeFromCart(Product product){
        cart.remove(product);
    }
}