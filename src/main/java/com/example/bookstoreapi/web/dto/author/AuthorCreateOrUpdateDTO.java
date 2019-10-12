package com.example.bookstoreapi.web.dto.author;

import lombok.Getter;

import java.util.List;

@Getter
public class AuthorCreateOrUpdateDTO {
    private String name;
    private List<Long> booksIds;
}
