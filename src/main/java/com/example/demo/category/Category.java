package com.example.demo.category;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_seq",
            sequenceName = "category_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_seq"
    )
    private Long id;

    private String name;

    public Category() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
