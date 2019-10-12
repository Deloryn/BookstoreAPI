package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.*;
import com.example.bookstoreapi.repository.AuthorRepository;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.repository.CategoryRepository;
import com.example.bookstoreapi.repository.PublisherRepository;
import com.example.bookstoreapi.web.dto.book.BasicBookInfoDTO;
import com.example.bookstoreapi.web.dto.book.BookCreateOrUpdateDTO;
import com.example.bookstoreapi.web.dto.book.DetailedBookInfoDTO;
import com.example.bookstoreapi.web.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private PublisherRepository publisherRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       CategoryRepository categoryRepository,
                       PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<BasicBookInfoDTO> getAllBooksBasicInfos() {
        return bookRepository
                .findAll()
                .stream()
                .map(BasicBookInfoDTO::new)
                .collect(Collectors.toList());
    }

    public BasicBookInfoDTO getBasicBookInfoById(Long id) {
        Book book = getBookById(id);
        return new BasicBookInfoDTO(book);
    }

    public DetailedBookInfoDTO getDetailedBookInfoById(Long id) {
        Book book = getBookById(id);
        return new DetailedBookInfoDTO(book);
    }

    public Long createBookFrom(BookCreateOrUpdateDTO dto) {
        Book book = mapToBook(dto);
        save(book);
        return book.getId();
    }

    public void updateBook(Long id, BookCreateOrUpdateDTO dto) {
        Book another = mapToBook(dto);
        Book existingBook = getBookById(id);
        existingBook.updateData(another);
        save(existingBook);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    private void save(Book book) {
        bookRepository.save(book);
    }

    private Book mapToBook(BookCreateOrUpdateDTO dto) {
        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setImgUrl(dto.getImgUrl());
        book.setPrice(dto.getPrice());
        book.setDescription(dto.getDescription());
        book.setReleaseDate(dto.getReleaseDate());
        book.setNumberInStore(dto.getNumberInStore());
        book.setNumberOfPages(dto.getNumberOfPages());

        List<Author> authors = getAuthorsByIds(dto.getAuthorsIds());
        List<Category> categories = getCategoriesByIds(dto.getCategoriesIds());
        Publisher publisher = getPublisherById(dto.getPublisherId());

        book.setAuthors(authors);
        book.setCategories(categories);
        book.setPublisher(publisher);

        return book;
    }

    private Book getBookById(Long id) {
        return getBookIfExists(id).orElseThrow(() -> new ResourceNotFoundException(Book.class, id));
    }

    private Optional<Book> getBookIfExists(Long id) {
        Book book = this.bookRepository.getOne(id);
        return Optional.ofNullable(book);
    }

    private Publisher getPublisherById(Long id) {
        return getPublisherIfExists(id).orElseThrow(() -> new ResourceNotFoundException(Publisher.class, id));
    }

    private Optional<Publisher> getPublisherIfExists(Long id) {
        Publisher publisher = publisherRepository.getOne(id);
        return Optional.ofNullable(publisher);
    }

    private List<Author> getAuthorsByIds(List<Long> authorIds) {
        return authorIds
                .stream()
                .map(id -> getAuthorIfExists(id)
                        .orElseThrow(() -> new ResourceNotFoundException(Author.class, id)))
                .collect(Collectors.toList());
    }

    private Optional<Author> getAuthorIfExists(Long authorId) {
        Author author = authorRepository.getOne(authorId);
        return Optional.ofNullable(author);
    }

    private List<Category> getCategoriesByIds(List<Long> categoryIds) {
        return categoryIds
                .stream()
                .map(id -> getCategoryIfExists(id)
                        .orElseThrow(() -> new ResourceNotFoundException(Category.class, id)))
                .collect(Collectors.toList());
    }

    private Optional<Category> getCategoryIfExists(Long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        return Optional.ofNullable(category);
    }

}
