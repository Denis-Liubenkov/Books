package com.tms.repository;

import com.tms.Book;

import java.io.IOException;
import java.util.List;

public interface BookRepository {
    List<Book> findAll() throws IOException;

    void save(Book book) throws IOException;

    void update(Book book) throws IOException;

    void delete(int id) throws IOException;
}
