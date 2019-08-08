package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Order  {

    private static int id = 0;
    private int orderId;
    private HashMap<Product,Integer> userCart;

    private String name;
    private float totalSum;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String shippingAddress;

    public Order(HashMap<Product,Integer> cart) {
        this.orderId = id++;
        this.userCart = cart;
    }

    public float sumTotal(){
        totalSum = 0;
        for (Map.Entry<Product, Integer> map : userCart.entrySet()) {
            float key = map.getKey().getDefaultPrice();
            Integer value = map.getValue();
            totalSum += (key * value);
        }
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }
}
