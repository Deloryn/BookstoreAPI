package com.example.bookstoreapi.web.dto.author;

import com.example.bookstoreapi.entity.Author;

public class AuthorInfoDTO {
    private Long id;
    private String name;

    public AuthorInfoDTO(Author author) {
        id = author.getId();
        name = author.getName();
    }
}
