package com.example.ecommercemangmentsystem.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String category;
    private  int  quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int count) {
        this.quantity = count;
    }
    // Constructor, Getters, Setters, toString()

    public Product(){

    }

    public Product( String name, double price, String category,int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ",quantity="+quantity+
                ", category='" + category + '\'' +
                '}';
    }
}



