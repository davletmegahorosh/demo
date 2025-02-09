package com.example.demo.category;

import com.example.demo.product.ProductListResponse;

import java.util.List;

public class CategoryDetailsResponse {
    private String categoryName;
    private List<ProductListResponse> products;

    // Конструктор
    public CategoryDetailsResponse(String categoryName, List<ProductListResponse> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    // Геттеры и сеттеры
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductListResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductListResponse> products) {
        this.products = products;
    }
}
