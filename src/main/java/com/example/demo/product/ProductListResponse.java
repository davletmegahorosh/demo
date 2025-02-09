package com.example.demo.product;

public class ProductListResponse {
    private String name;
    private int price;

    // Конструктор
    public ProductListResponse(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
