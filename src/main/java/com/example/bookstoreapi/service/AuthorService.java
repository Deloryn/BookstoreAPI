package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.Author;
import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.repository.AuthorRepository;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.web.dto.author.AuthorCreateOrUpdateDTO;
import com.example.bookstoreapi.web.dto.author.AuthorInfoDTO;
import com.example.bookstoreapi.web.dto.book.BasicBookInfoDTO;
import com.example.bookstoreapi.web.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository,
                         BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<AuthorInfoDTO> getAllAuthorsInfos() {
        return authorRepository
                .findAll()
                .stream()
                .map(AuthorInfoDTO::new)
                .collect(Collectors.toList());
    }

    public AuthorInfoDTO getAuthorInfoById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Author.class, id));
        return new AuthorInfoDTO(author);
    }

    public List<BasicBookInfoDTO> getAuthorBooksInfo(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Author.class, id))
                .getBooks()
                .stream()
                .map(BasicBookInfoDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Author.class, id));
        authorRepository.delete(author);
    }

    public Long createAuthorFrom(AuthorCreateOrUpdateDTO dto) {
        Author author = mapToAuthor(dto);
        authorRepository.save(author);
        return author.getId();
    }

    public void updateAuthor(Long id, AuthorCreateOrUpdateDTO dto) {
        Author anotherAutor = mapToAuthor(dto);
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Author.class, id));
        existingAuthor.updateData(anotherAutor);
        authorRepository.save(existingAuthor);
    }

    private Author mapToAuthor(AuthorCreateOrUpdateDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());

        List<Book> books = getBooksByIds(dto.getBooksIds());
        author.setBooks(books);

        return author;
    }

    private List<Book> getBooksByIds(List<Long> booksIds) {
        return booksIds
                .stream()
                .map(id -> bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(Book.class, id)))
                .collect(Collectors.toList());
    }
}
