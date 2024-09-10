package com.tms.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.tms.Book;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Repository
public class CsvBookRepository implements BookRepository {

    private final String csvFile = "src/main/resources/books.csv";
    private final CsvMapper csvMapper = new CsvMapper();

    @Override
    public List<Book> findAll() throws IOException {
        CsvSchema csvSchema = csvMapper.schemaFor(Book.class).withHeader();
        MappingIterator<Book> iterator = csvMapper.readerFor(Book.class).with(csvSchema).readValues(new File(csvFile));
        return iterator.readAll();
    }

    @Override
    public Book findById(Integer id) throws IOException {
        List<Book> books = findAll();
        for (Book book : books) {
            if (Objects.equals(book.getId(), id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void save(Book book) throws IOException {
        List<Book> books = findAll();
        books.add(book);
        csvMapper.writer(csvMapper.schemaFor(Book.class).withHeader()).writeValue(new File(csvFile), books);
    }

    @Override
    public void update(Book book) throws IOException {
        List<Book> books = findAll();
        for (int i = 0; i < books.size(); i++) {
            if (Objects.equals(books.get(i).getId(), book.getId())) {
                books.set(i, book);
                break;
            }
        }
        csvMapper.writer(csvMapper.schemaFor(Book.class).withHeader()).writeValue(new File(csvFile), books);
    }

    @Override
    public void delete(int id) throws IOException {
        List<Book> books = findAll();
        books.removeIf(b -> b.getId() == id);
        csvMapper.writer(csvMapper.schemaFor(Book.class).withHeader()).writeValue(new File(csvFile), books);
    }
}
