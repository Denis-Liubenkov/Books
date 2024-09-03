package com.tms;

import com.tms.service.BookService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApp {
    private final BookService bookService;

    public ConsoleApp(BookService bookService) {
        this.bookService = bookService;
    }

    public void begin() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберете опцию: \n 1. Вывести список книг \n 2. Создать новую книгу \n 3. " +
                    "Отредактировать книгу \n 4. Удалить книгу \n 5. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Book> bookList = bookService.getAllBooks();
                    for (Book book : bookList) {
                        System.out.println(book);
                    }
                    break;
                case 2:
                    Book book = new Book();
                    System.out.println("Введите ID: ");
                    book.setId(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Введите название: ");
                    book.setTitle(scanner.nextLine());
                    System.out.println("Введите автора: ");
                    book.setAuthor(scanner.nextLine());
                    System.out.println("Введите описание: ");
                    book.setDescription(scanner.nextLine());
                    bookService.addBook(book);
                    break;
                case 3:
                    System.out.println("Введите ID книги для редактирования: ");
                    int editId = scanner.nextInt();
                    Book bookToEdit = new Book();
                    bookToEdit.setId(editId);
                    scanner.nextLine();
                    System.out.println("Введите новое название: ");
                    bookToEdit.setTitle(scanner.nextLine());
                    System.out.println("Введите нового автора: ");
                    bookToEdit.setAuthor(scanner.nextLine());
                    System.out.println("Введите новое описание: ");
                    bookToEdit.setDescription(scanner.nextLine());
                    bookService.updateBook(bookToEdit);
                    break;
                case 4:
                    System.out.println("Введите ID книги для удаления: ");
                    int deleteId = scanner.nextInt();
                    bookService.deleteBook(deleteId);
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неправильный ввод, попробуйте снова.");
            }
        }
    }
}
