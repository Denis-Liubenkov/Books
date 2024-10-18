package com.tms.repository;

import com.tms.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CsvBookRepository implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public CsvBookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.queryForList(sql);
    }

    public Book findById(Integer id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        Map<String, Object> bookData = jdbcTemplate.queryForMap(sql, id);
        Book book = new Book(
                (Integer) bookData.get("id"),
                (String) bookData.get("title"),
                (String) bookData.get("description"));
        return book;
    }

    public void save(Book book) {
        String sql = "INSERT INTO books VALUES (?,?,?)";
        jdbcTemplate.update(sql,
                book.getId(),
                book.getTitle(),
                book.getDescription());
    }

    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                book.getTitle(),
                book.getDescription(),
                book.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
