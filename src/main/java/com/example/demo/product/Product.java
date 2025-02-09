package com.example.demo.product;

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

    private String name;
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Product() {}
}
