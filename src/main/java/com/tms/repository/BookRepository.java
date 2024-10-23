package com.tms.repository;

import com.tms.domain.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository {
    List<Book> findAll() throws SQLException;

    Book findById(Integer id);

    void save(Book book);

    void update(Book book);

    void delete(Integer id);
}
