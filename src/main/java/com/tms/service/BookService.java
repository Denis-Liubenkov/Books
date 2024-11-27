package com.tms.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.tms.domain.Book;
import com.tms.exceptions.ImageNotFoundException;
import com.tms.repository.BookRepository;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GridFsTemplate gridFsTemplate;

    public BookService(BookRepository bookRepository, GridFsTemplate gridFsTemplate) {
        this.bookRepository = bookRepository;
        this.gridFsTemplate = gridFsTemplate;
    }

    public Book addBookWithImage(Book book, MultipartFile image) throws IOException {
        ObjectId imageId = gridFsTemplate.store(image.getInputStream(), image.getOriginalFilename(), image.getContentType());
        book.setImageId(imageId);
        return bookRepository.save(book);
    }

    public Book getBookWithImage(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public GridFsResource getImage(ObjectId imageId) {
        try {
            GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(imageId)));

            return new GridFsResource(gridFsFile);

        } catch (IllegalArgumentException e) {
            throw new ImageNotFoundException("Invalid image ID: " + imageId, e);
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);

    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
