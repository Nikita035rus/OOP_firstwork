package Interview.oop.controller;

import Interview.oop.exception.IncorrectRequest;
import Interview.oop.packageservice.BookService;
import Interview.oop.repository.BookRepository;

import java.util.Scanner;

public class BookController {
    private String arguments;
    private final BookService bookService = new BookService(new BookRepository());

    public BookController() {
        Scanner scn = new Scanner(System.in);
        while (true){
        getRequest(scn.nextLine());}
    }

    public void getRequest(String request) {
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
                findBookByName();
                break;
            case "find_books":
                findAllBooks();
                break;
            case "find_books_price_between":
                findBookWherePriceBetween();
                break;
            case "save_book":
                saveBook();
                break;
            case "update_book":
                updateBookByID();
                break;
            case "delete_book":
                deleteBookByName();
                break;
            case "delete_all":
                deleteAll();
                break;
            default:
                throw new IncorrectRequest("IncorrectRequestException");
        }
    }

    public void findBookByName() {
        System.out.println(
                bookService.findBookByName(arguments).orElseThrow(() ->
                        new IncorrectRequest("TheBookIsEmptyException"))
        );
    }

    public void findAllBooks() {
        bookService.findAllBooks()
                .forEach(System.out::println);
    }

    public void findBookWherePriceBetween() {
        bookService.findBookWherePriceBetween(arguments)
                .forEach(System.out::println);
    }

    public void saveBook() {
        System.out.println(bookService.saveBook(arguments));
    }

    public void updateBookByID() {
        System.out.println(bookService.updateBookByID(arguments));
    }

    public void deleteBookByName() {
        bookService.deleteBookByName(arguments);
    }

    public void deleteAll() {
        bookService.deleteAll();
    }
}
