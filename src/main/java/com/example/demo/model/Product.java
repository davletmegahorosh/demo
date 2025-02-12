package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table
public class Product {

    @Id
    @SequenceGenerator(
            name = "products",
            sequenceName = "products",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "products"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String name;
    private int price;

    private String description;
    private String imageBase64;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Product() {}
    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
