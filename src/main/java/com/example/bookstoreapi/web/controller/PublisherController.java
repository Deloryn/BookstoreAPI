package com.example.bookstoreapi.web.controller;

import com.example.bookstoreapi.service.PublisherService;
import com.example.bookstoreapi.web.dto.book.BasicBookInfoDTO;
import com.example.bookstoreapi.web.dto.publisher.PublisherCreateOrUpdateDTO;
import com.example.bookstoreapi.web.dto.publisher.PublisherInfoDTO;
import com.example.bookstoreapi.web.error.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PublisherController {
    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(value = "/publishers")
    public ResponseEntity<List<PublisherInfoDTO>> getAllPublishersInfos() {
        List<PublisherInfoDTO> dtos = publisherService.getAllPublishersInfos();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/publishers/{id}")
    public ResponseEntity<PublisherInfoDTO> getPublisherInfoById(@PathVariable Long id) {
        PublisherInfoDTO dto = publisherService.getPublisherInfoById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/publishers/{id}/books")
    public ResponseEntity<List<BasicBookInfoDTO>> getPublisherBooksInfo(@PathVariable Long id) {
        List<BasicBookInfoDTO> dtos = publisherService.getPublisherBooksInfo(id);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(value = "/publishers")
    public ResponseEntity<Long> createPublisher(@Valid @RequestBody PublisherCreateOrUpdateDTO dto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        Long id = publisherService.createPublisherFrom(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping(value = "/publishers/{id}")
    public ResponseEntity<Long> updatePublisher(@PathVariable Long id,
                                             @Valid @RequestBody PublisherCreateOrUpdateDTO dto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        publisherService.updatePublisher(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping(value = "/publishers/{id}")
    public ResponseEntity<Long> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.ok(id);
    }
}
