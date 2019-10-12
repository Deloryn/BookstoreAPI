package com.example.bookstoreapi.web.controller;

import com.example.bookstoreapi.service.BookService;
import com.example.bookstoreapi.web.dto.book.BasicBookInfoDTO;
import com.example.bookstoreapi.web.dto.book.BookCreateOrUpdateDTO;
import com.example.bookstoreapi.web.dto.book.DetailedBookInfoDTO;
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

    @GetMapping(value = "/books")
    public ResponseEntity<List<BasicBookInfoDTO>> getAllBooksBasicInfos() {
        List<BasicBookInfoDTO> dtos = bookService.getAllBooksBasicInfos();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BasicBookInfoDTO> getBasicBookInfoById(@PathVariable Long id) {
        BasicBookInfoDTO dto = bookService.getBasicBookInfoById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/books/{id}/details")
    public ResponseEntity<DetailedBookInfoDTO> getDetailedBookInfoById(@PathVariable Long id) {
        DetailedBookInfoDTO dto = bookService.getDetailedBookInfoById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/books")
    public ResponseEntity<Long> createBook(@RequestBody BookCreateOrUpdateDTO dto) {
        Long id = bookService.createBookFrom(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping(value = "/books/{id}")
    public ResponseEntity<Long> updateBook(@PathVariable Long id,
                                           @RequestBody BookCreateOrUpdateDTO dto) {
        bookService.updateBook(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(id);
    }
}
