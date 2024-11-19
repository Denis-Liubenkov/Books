package com.tms.controller;

import com.tms.domain.Book;
import com.tms.exceptions.BookNotFoundException;
import com.tms.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get list of books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of books are found"),
            @ApiResponse(responseCode = "404", description = "List of books are not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),})
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        if (bookList.isEmpty()) {
            log.info("List of books are not found!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("List of books are found!");
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get one book", description = "Get one book , need to pass the input parameter book`s id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book is found"),
            @ApiResponse(responseCode = "404", description = "Book is not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),})
    @GetMapping("/{id}")
    public ResponseEntity<Book> getOneBook(@PathVariable("id") @Parameter(description = "it is book`s id") Integer id) {
        Book book = bookService.getBookById(id).orElseThrow(BookNotFoundException::new);
        log.info("Book with id: " + id + " is found!");
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Operation(summary = "Creating book", description = "Create book, need to pass the input parameter object Book in format JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book is created"),
            @ApiResponse(responseCode = "409", description = "Book is not created"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),})
    @PostMapping
    public ResponseEntity<HttpStatus> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        log.info("Book with id: " + book.getId() + " is created!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Updating book", description = "Update book, need to pass the input parameter object Book in format JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book is updated"),
            @ApiResponse(responseCode = "409", description = "Book is not updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),})
    @PutMapping
    public ResponseEntity<HttpStatus> updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        log.info("Book with id: " + book.getId() + " is updated!");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deleting book", description = "Delete book,  need to pass the input parameter book`s id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book is deleted"),
            @ApiResponse(responseCode = "409", description = "Book is not deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),})
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        log.info("Book with id: " + id + " is deleted!");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

