package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.web.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        Book book = this.bookRepository.getOne(id);
        return Optional.of(book);
    }

    public void updateExistingBook(Book existingBook, Book updatedBook) {
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setCategories(updatedBook.getCategories());
        existingBook.setAuthors(updatedBook.getAuthors());
        existingBook.setDetails(updatedBook.getDetails());
        existingBook.setReviews(updatedBook.getReviews());
        existingBook.setRegularPrice(updatedBook.getRegularPrice());
        existingBook.setDiscountedPrice(updatedBook.getDiscountedPrice());
        existingBook.setImgUrl(updatedBook.getImgUrl());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setIsAvailable(updatedBook.getIsAvailable());
        save(existingBook);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

}
