package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book_list/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> bookList() {
        return bookService.bookList();
    }

    @PostMapping
    public void addBook(@RequestBody Book book){
        bookService.addNewBook(book);
    }

}
