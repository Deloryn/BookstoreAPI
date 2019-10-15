package com.example.bookstoreapi.web.controller;

import com.example.bookstoreapi.service.AuthorService;
import com.example.bookstoreapi.web.dto.author.AuthorCreateOrUpdateDTO;
import com.example.bookstoreapi.web.dto.author.AuthorInfoDTO;
import com.example.bookstoreapi.web.dto.book.BasicBookInfoDTO;
import com.example.bookstoreapi.web.error.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/authors")
    public ResponseEntity<List<AuthorInfoDTO>> getAllAuthorsInfos() {
        List<AuthorInfoDTO> dtos = authorService.getAllAuthorsInfos();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<AuthorInfoDTO> getAuthorInfoById(@PathVariable Long id) {
        AuthorInfoDTO dto = authorService.getAuthorInfoById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/authors/{id}/books")
    public ResponseEntity<List<BasicBookInfoDTO>> getAuthorBooksInfo(@PathVariable Long id) {
        List<BasicBookInfoDTO> dtos = authorService.getAuthorBooksInfo(id);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(value = "/authors")
    public ResponseEntity<Long> createAuthor(@Valid @RequestBody AuthorCreateOrUpdateDTO dto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        Long id = authorService.createAuthorFrom(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping(value = "/authors/{id}")
    public ResponseEntity<Long> updateAuthor(@PathVariable Long id,
                                             @Valid @RequestBody AuthorCreateOrUpdateDTO dto,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidInputException(bindingResult.getFieldErrors());
        }
        authorService.updateAuthor(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping(value = "/authors/{id}")
    public ResponseEntity<Long> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(id);
    }
}
