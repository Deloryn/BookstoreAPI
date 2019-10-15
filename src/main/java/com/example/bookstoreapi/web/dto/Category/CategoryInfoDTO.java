package com.example.bookstoreapi.web.dto.Category;

import com.example.bookstoreapi.entity.Category;

public class CategoryInfoDTO {
    private Long id;
    private String name;

    public CategoryInfoDTO(Category category) {
        id = category.getId();
        name = category.getName();
    }
}
