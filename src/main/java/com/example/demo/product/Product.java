package com.example.demo.product;

import com.example.demo.category.Category;
import com.example.demo.user.User;
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
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private int price;

    public User getAuthor() {
        return author;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
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


    public void setAuthor(User author) {
        this.author = author;
    }

    public Product() {}
}
