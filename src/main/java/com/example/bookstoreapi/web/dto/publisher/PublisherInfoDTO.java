package com.example.bookstoreapi.web.dto.publisher;

import com.example.bookstoreapi.entity.Publisher;

public class PublisherInfoDTO {
    private Long id;
    private String name;

    public PublisherInfoDTO(Publisher publisher) {
        id = publisher.getId();
        name = publisher.getName();
    }
}
