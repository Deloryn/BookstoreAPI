package com.example.bookstoreapi.web.controller;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.service.BookService;
import com.example.bookstoreapi.web.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/books")
    public ResponseEntity<Book> create(@RequestBody Book book) {
        bookService.save(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) throws ResourceNotFoundException {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Book.class, id));
        return ResponseEntity.ok(book);
    }

    @PutMapping(value = "/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                           @RequestBody Book updatedBook) throws ResourceNotFoundException {
        Book existingBook = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Book.class, id));
        bookService.updateExistingBook(existingBook, updatedBook);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) throws ResourceNotFoundException {
        Book existingBook = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Book.class, id));
        bookService.deleteBook(existingBook);
        return ResponseEntity.ok(existingBook);
    }
}
