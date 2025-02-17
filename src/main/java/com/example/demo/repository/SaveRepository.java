package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.model.Save;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaveRepository extends JpaRepository<Save, Long> {

    Optional<Save> findByProductAndAuthor(Product product, User author);

    List<Save> findByAuthor(User author);
}
