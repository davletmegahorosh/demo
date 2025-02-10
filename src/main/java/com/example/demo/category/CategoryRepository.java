package com.example.demo.category;

import com.example.demo.category.Category;
import com.example.demo.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT b FROM Category b WHERE b.name = ?1")
    Optional<Category> findCategoryByName(String name);

}