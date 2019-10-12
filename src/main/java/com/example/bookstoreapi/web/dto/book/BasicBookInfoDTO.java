package com.example.bookstoreapi.web.dto.book;

import com.example.bookstoreapi.entity.Author;
import com.example.bookstoreapi.entity.Book;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class BasicBookInfoDTO {
    private Long id;
    private String imgUrl;
    private String title;
    private String authors;
    private BigDecimal price;
    private Boolean isAvailable;

    public BasicBookInfoDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.imgUrl = book.getImgUrl();
        this.isAvailable = book.getNumberInStore() > 0;
        this.price = book.getPrice();
        this.authors = concatBookAuthorsNames(book);
    }

    private String concatBookAuthorsNames(Book book) {
        return book
                .getAuthors()
                .stream()
                .map(Author::getName)
                .collect(Collectors.joining(", "));
    }
}
