package com.example.bookstoreapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Short rating;

    private String opinion;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
