package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.name = ?1")
    Optional<Book> findBookByName(String name);

    @Query("SELECT b FROM Book b WHERE b.price > ?1 and b.price < ?2")
    Optional<Book> findBookByPrice(float min, float max);

}