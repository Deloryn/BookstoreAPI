package com.example.bookstoreapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer numberInStore;

    @Column(nullable = false)
    private Integer numberOfPages;

    @Column(nullable = false)
    private Date releaseDate;

    private String description;

    @OneToOne(mappedBy = "details")
    private Book book;
}
