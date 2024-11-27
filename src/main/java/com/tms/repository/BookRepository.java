package com.tms.repository;

import com.tms.domain.Book;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {
    @NonNull List<Book> findAll();

    @NonNull Optional<Book> findById(@NonNull String id);

    void deleteById(@NonNull String id);
}
