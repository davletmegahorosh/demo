package com.example.demo.save;

import com.example.demo.product.Product;
import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaveRepository extends JpaRepository<Save, Long> {

    Optional<Save> findByProductAndAuthor(Product product, User author);

    List<Save> findByAuthor(User author);
}
