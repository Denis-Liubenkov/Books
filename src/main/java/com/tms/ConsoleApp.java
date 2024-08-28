package com.tms;

import com.tms.service.BookService;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private final BookService bookService;

    public ConsoleApp(BookService bookService) {
        this.bookService = bookService;
    }

    public void begin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберете опцию: \\п1. Вывести список книг \\п2. Создать новую книгу \\п3. " +
                    "Отредактировать книгу \\п4. Удалить книгу \\п5. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    List<Book> bookList = bookService.getAllBooks();
                    bookList.forEach(book ->
                            System.out.println(book.getId() + ": " + book.getTitle()));
                }
                case 2 -> {
                    bookService.addBook();

                }
                case 3 -> {
                    bookService.updateBook();

                }
                case 4 -> {
                    bookService.deleteBook();

                }
                case 5 -> {
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Неправильный ввод");
            }
        }
    }
}
