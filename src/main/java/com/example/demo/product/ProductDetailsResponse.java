package com.example.demo.product;

public class ProductDetailsResponse {
    private String name;
    private int price;
    private String category;
    private String author;

    // Конструктор
    public ProductDetailsResponse(String name, int price, String category, String author) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.author = author;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
