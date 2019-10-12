package com.example.bookstoreapi.web.dto.book;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.web.dto.author.AuthorInfoDTO;
import com.example.bookstoreapi.web.dto.publisher.PublisherInfoDTO;
import com.example.bookstoreapi.web.dto.review.ReviewInfoDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DetailedBookInfoDTO {
    private Long id;
    private String imgUrl;
    private String title;
    private BigDecimal price;
    private Boolean isAvailable;
    private Date releaseDate;
    private Integer numberOfPages;
    private List<ReviewInfoDTO> reviews;
    private List<AuthorInfoDTO> authors;
    private PublisherInfoDTO publisher;

    public DetailedBookInfoDTO(Book book) {
        id = book.getId();
        imgUrl = book.getImgUrl();
        title = book.getTitle();
        price = book.getPrice();
        isAvailable = book.getNumberInStore() > 0;
        releaseDate = book.getReleaseDate();
        numberOfPages = book.getNumberOfPages();
        publisher = new PublisherInfoDTO(book.getPublisher());
        reviews = book.getReviews()
                .stream()
                .map(ReviewInfoDTO::new)
                .collect(Collectors.toList());
        authors = book.getAuthors()
                .stream()
                .map(AuthorInfoDTO::new)
                .collect(Collectors.toList());
    }
}
