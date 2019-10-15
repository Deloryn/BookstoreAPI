package com.example.bookstoreapi.repository;

import com.example.bookstoreapi.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Override
    Optional<Publisher> findById(Long id);
}
