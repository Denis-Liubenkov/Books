package com.tms.repository;

import com.tms.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

    Book findById(int id);

    void save(Book book);

    void update(Book book);

    void delete(int id);
}
