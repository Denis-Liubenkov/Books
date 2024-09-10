package com.tms;

import com.tms.service.BookService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Component
public class ConsoleApp {
    private final BookService bookService;

    private final MessageSource messageSource;

    public ConsoleApp(BookService bookService, MessageSource messageSource) {
        this.bookService = bookService;
        this.messageSource = messageSource;
    }

    public void begin() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose language (en/ru):");
        String language = scanner.nextLine();
        Locale locale = Locale.forLanguageTag(language);
        while (true) {

            System.out.println(messageSource.getMessage("menu.choice", null, locale));
            System.out.println(messageSource.getMessage("menu.showBooks", null, locale));
            System.out.println(messageSource.getMessage("menu.createBook", null, locale));
            System.out.println(messageSource.getMessage("menu.updateBook", null, locale));
            System.out.println(messageSource.getMessage("menu.deleteBook", null, locale));
            System.out.println(messageSource.getMessage("menu.exit", null, locale));

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    try {
                        List<Book> bookList = bookService.getAllBooks();
                        if (bookList.isEmpty()) {
                            System.out.println(messageSource.getMessage("message.emptyBookList", null, locale));
                        } else {
                            for (Book book : bookList) {
                                System.out.println(book);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(messageSource.getMessage("error.getBooks", null, locale) + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        System.out.println(messageSource.getMessage("input.title", null, locale));
                        String title = scanner.nextLine();
                        System.out.println(messageSource.getMessage("input.author", null, locale));
                        String author = scanner.nextLine();
                        System.out.println(messageSource.getMessage("input.description", null, locale));
                        String description = scanner.nextLine();
                        int newId = (bookService.getAllBooks().size() + 1);
                        Book book = new Book();
                        book.setId(newId);
                        book.setTitle(title);
                        book.setAuthor(author);
                        book.setDescription(description);
                        bookService.createBook(book);
                        System.out.println(messageSource.getMessage("message.bookCreated", null, locale));
                    } catch (IOException e) {
                        System.out.println(messageSource.getMessage("error.createBook", null, locale) + e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        System.out.println(messageSource.getMessage("input.bookId", null, locale));
                        int editId = scanner.nextInt();
                        scanner.nextLine();
                        Book book = bookService.getBookById(editId);
                        if (book != null) {
                            System.out.println(messageSource.getMessage("input.newTitle", null, locale));
                            String title = scanner.nextLine();
                            book.setTitle(title);
                            System.out.println(messageSource.getMessage("input.newAuthor", null, locale));
                            String author = scanner.nextLine();
                            book.setAuthor(author);
                            System.out.println(messageSource.getMessage("input.newDescription", null, locale));
                            String description = scanner.nextLine();
                            book.setDescription(description);
                            bookService.updateBook(book);
                            System.out.println(messageSource.getMessage("message.bookUpdated", null, locale));
                        } else {
                            System.out.println(messageSource.getMessage("message.bookNotFound", null, locale));
                        }
                    } catch
                    (IOException e) {
                        System.out.println(messageSource.getMessage("error.updateBook", null, locale) + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        System.out.println(messageSource.getMessage("input.bookId", null, locale));
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        bookService.deleteBook(id);
                        System.out.println(messageSource.getMessage("message.bookDeleted", null, locale));
                    } catch (IOException e) {
                        System.out.println(messageSource.getMessage("error.deleteBook", null, locale) + e.getMessage());
                    }
                }
                case 5 -> {
                    System.out.println(messageSource.getMessage("message.exit", null, locale));
                    scanner.close();
                    return;
                }
                default -> System.out.println(messageSource.getMessage("message.invalidChoice", null, locale));
            }
        }
    }
}
