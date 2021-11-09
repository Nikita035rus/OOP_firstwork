package Interview.oop.controller;

import Interview.oop.exception.IncorrectRequest;
import Interview.oop.packageservice.BookService;
import Interview.oop.repository.BookRepository;

import java.util.Scanner;

public class BookController {
    private static String request;
    private static String arguments;

    public BookController() {
        request = new Scanner(System.in).nextLine();
        getRequest(new BookService(new BookRepository()));
    }


    public static void start() {
        while (true) {
            new BookController();
        }
    }

    public void getRequest(BookService bookService) {
        String command;

        if (!request.contains("?")) {
            command = request;
        } else {
            command = request
                    .substring(0, request.indexOf("?"));
            arguments = request
                    .substring(request.indexOf("?") + 1);
        }

        switch (command) {
            case "find_book":
                findBookByName(bookService);
                break;
            case "find_books":
                findAllBooks(bookService);
                break;
            case "find_books_price_between":
                findBookWherePriceBetween(bookService);
                break;
            case "save_book":
                saveBook(bookService);
                break;
            case "update_book":
                updateBookByID(bookService);
                break;
            case "delete_book":
                deleteBookByName(bookService);
                break;
            case "delete_all":
                deleteAll(bookService);
                break;
            default:
                throw new IncorrectRequest("IncorrectRequestException");
        }
    }

    public void findBookByName(BookService bookService) {
        System.out.println(
               bookService.findBookByName(arguments).orElseThrow(() ->
                        new IncorrectRequest("TheBookIsEmptyException"))
        );
    }

    public void findAllBooks(BookService bookService) {
        bookService.findAllBooks()
                .forEach(System.out::println);
    }

    public void findBookWherePriceBetween(BookService bookService) {
        bookService.findBookWherePriceBetween(arguments)
                .forEach(System.out::println);
    }

    public void saveBook(BookService bookService) {
        System.out.println(bookService.saveBook(arguments));
    }

    public void updateBookByID(BookService bookService) {
        System.out.println(bookService.updateBookByID(arguments));
    }

    public void deleteBookByName(BookService bookService) {
        bookService.deleteBookByName(arguments);
    }

    public void deleteAll(BookService bookService) {
         bookService.deleteAll();
    }
}
