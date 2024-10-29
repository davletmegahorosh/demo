package com.example.demo.book;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> bookList() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException(
                        "Книга с id " + bookId + " не найдена"));
    }


    public void addNewBook(Book book) {
        Optional<Book> bookByName = bookRepository
                .findBookByName(book.getName());
        if (bookByName.isPresent()) {
            throw new IllegalStateException("Книга с таким названием уже существуется");

        }
        bookRepository.save(book);
    }

    public void deleteBook(Long book_id) {
        boolean exists = bookRepository.existsById(book_id);
        if (!exists) {
            throw new IllegalStateException("book with id " + " doesnt'exists");
        }
        bookRepository.deleteById(book_id);
    }


    @Transactional
    public void updateBook(Long bookId, String name,
                           int pages, int price,
                           String author) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException(
                        "book with id " + bookId + " doesn't exists"));

        if (name != null && name.length() > 0 && !Objects.equals(book.getName(), name)) {
            book.setName(name);
        }

        if (pages > 0 && !Objects.equals(book.getPages(), pages)) {
            book.setPages(pages);
        }

        if (price > 0 && !Objects.equals(book.getPrice(), price)) {
            book.setPrice(price);
        }

        if (author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)) {
            book.setAuthor(author);
        }

        bookRepository.save(book);
    }
}