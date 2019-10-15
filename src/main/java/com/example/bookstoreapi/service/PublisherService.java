package com.example.bookstoreapi.service;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.entity.Publisher;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.repository.PublisherRepository;
import com.example.bookstoreapi.web.dto.book.BasicBookInfoDTO;
import com.example.bookstoreapi.web.dto.publisher.PublisherCreateOrUpdateDTO;
import com.example.bookstoreapi.web.dto.publisher.PublisherInfoDTO;
import com.example.bookstoreapi.web.error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;
    private BookRepository bookRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository,
                            BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    public List<PublisherInfoDTO> getAllPublishersInfos() {
        return publisherRepository
                .findAll()
                .stream()
                .map(PublisherInfoDTO::new)
                .collect(Collectors.toList());
    }

    public PublisherInfoDTO getPublisherInfoById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Publisher.class, id));
        return new PublisherInfoDTO(publisher);
    }

    public List<BasicBookInfoDTO> getPublisherBooksInfo(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Publisher.class, id))
                .getBooks()
                .stream()
                .map(BasicBookInfoDTO::new)
                .collect(Collectors.toList());
    }

    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Publisher.class, id));
        publisherRepository.delete(publisher);
    }

    public Long createPublisherFrom(PublisherCreateOrUpdateDTO dto) {
        Publisher publisher = mapToPublisher(dto);
        publisherRepository.save(publisher);
        return publisher.getId();
    }

    public void updatePublisher(Long id, PublisherCreateOrUpdateDTO dto) {
        Publisher anotherPublisher = mapToPublisher(dto);
        Publisher existingPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Publisher.class, id));
        existingPublisher.updateData(anotherPublisher);
        publisherRepository.save(existingPublisher);
    }

    private Publisher mapToPublisher(PublisherCreateOrUpdateDTO dto) {
        Publisher publisher = new Publisher();
        publisher.setName(dto.getName());

        List<Book> books = getBooksByIds(dto.getBooksIds());
        publisher.setBooks(books);

        return publisher;
    }

    private List<Book> getBooksByIds(List<Long> booksIds) {
        return booksIds
                .stream()
                .map(id -> bookRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(Book.class, id)))
                .collect(Collectors.toList());
    }
}
