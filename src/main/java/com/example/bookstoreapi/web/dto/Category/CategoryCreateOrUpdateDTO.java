package com.example.bookstoreapi.web.dto.Category;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class CategoryCreateOrUpdateDTO {
    @NotBlank
    private String name;

    @NotNull
    private List<Long> booksIds;
}
