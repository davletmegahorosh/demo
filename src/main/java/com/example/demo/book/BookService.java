package com.example.demo.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    public List<Book> bookList(){
        return List.of(
                new Book(
                        1,
                        "1984",
                        270,
                        300,
                        "J.Oruel"
                ),
                new Book(
                        2,
                        "Горжость и предубеждение",
                        383,
                        400,
                        "Jane Osten"
                )
        );
    }
}
