package com.tms.repository;

import com.tms.domain.Book;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @NonNull
    List<Book> findAll();

    @NonNull
    Optional<Book> findById(@NonNull Integer id);

    void deleteById(@NonNull Integer id);
}
