package com.tms.service;

import com.tms.domain.Book;
import com.tms.repository.CsvBookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final CsvBookRepository csvBookRepository;

    public BookService(CsvBookRepository csvBookRepository) {
        this.csvBookRepository = csvBookRepository;
    }

    public List<Book> getAllBooks() {
        return csvBookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return csvBookRepository.findById(id);
    }

    public void createBook(Book book) {
        csvBookRepository.save(book);
    }

    public void updateBook(Book book) {
        csvBookRepository.update(book);
    }

    public void deleteBook(Integer id) {
        csvBookRepository.delete(id);
    }
}
