package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "{book_id}")
    public Book getBookById(@PathVariable("book_id") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book){
        bookService.addNewBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Книга добавлена успешно");
    }

    @DeleteMapping(path = "{book_id}")
    public ResponseEntity<String> deleteBook(@PathVariable("book_id") Long book_id){
        bookService.deleteBook(book_id);
        return ResponseEntity.ok("Книга удалена успешно");
    }

    @PutMapping(path = "{book_id}")
    public ResponseEntity<String> updateBook(
            @PathVariable("book_id") Long book_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int pages,
            @RequestParam(required = false) int price,
            @RequestParam(required = false) String author){
        bookService.updateBook(book_id, name, pages, price, author);
        return ResponseEntity.ok("Книга обновлена успешно");
    }
}
