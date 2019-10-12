package com.example.bookstoreapi.web.dto.book;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
public class BookCreateOrUpdateDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String imgUrl;

    @NotNull
    private BigDecimal price;

    @NotNull
    private List<Long> authorIds;

    @NotNull
    private List<Long> categoryIds;

    @NotNull
    private Long publisherId;

    @NotNull
    private Integer numberInStore;

    @NotNull
    private Integer numberOfPages;

    @NotNull
    private Date releaseDate;

    private String description;
}
