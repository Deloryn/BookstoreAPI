package com.example.bookstoreapi.web.dto.author;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class AuthorCreateOrUpdateDTO {
    @NotBlank
    private String name;

    @NotNull
    private List<Long> booksIds;
}
