package com.tms.service;

import com.tms.Book;
import com.tms.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() throws IOException {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) throws IOException {
        return bookRepository.findById(id);
    }

    public void createBook(Book book) throws IOException {
        bookRepository.save(book);
    }

    public void updateBook(Book book) throws IOException {
        bookRepository.update(book);
    }

    public void deleteBook(int id) throws IOException {
        bookRepository.delete(id);
    }
}
