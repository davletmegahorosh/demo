package com.example.demo.product;

public class ProductListResponse {
    private String name;
    private int price;
    private String imageBase64;


    public ProductListResponse(String name, int price, String imageBase64) {
        this.name = name;
        this.price = price;
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
}
