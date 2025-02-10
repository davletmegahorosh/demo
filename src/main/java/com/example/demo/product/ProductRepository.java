package com.example.demo.product;

import com.example.demo.category.Category;
import com.example.demo.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT b FROM Product b WHERE b.name = ?1")
    Optional<Product> findProductByName(String name);

    @Query("SELECT b FROM Product b WHERE b.price > ?1 and b.price < ?2")
    Optional<Product> findProductByPrice(float min, float max);

    List<Product> findByCategory(Category category);

}