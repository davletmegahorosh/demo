package com.example.demo.product;

public class ProductDetailsResponse {
    private String name;
    private int price;
    private String category;
    private String author;
    private String description;
    private String imageBase64;

    public ProductDetailsResponse(String name, int price, String category, String author, String description, String imageBase64) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.author = author;
        this.description = description;
        this.imageBase64 = imageBase64;

    }

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
