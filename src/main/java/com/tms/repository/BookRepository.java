package com.tms.repository;

import com.tms.domain.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface BookRepository {
    List<Map<String, Object>> findAll() throws SQLException;

    Book findById(Integer id);

    void save(Book book);

    void update(Book book);

    void delete(int id);
}
