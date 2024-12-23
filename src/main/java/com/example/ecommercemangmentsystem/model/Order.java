package com.example.ecommercemangmentsystem.model;

import java.util.Date;
import java.util.List;

public class Order {
    private List<CartItem> orderedItems;
    private double totalPrice;
    private Date orderDate;
    public Order(){}


    public Order(List<CartItem> orderedItems, double totalPrice, Date orderDate) {
        this.orderedItems = orderedItems;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public List<CartItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<CartItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedItems=" + orderedItems +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}



