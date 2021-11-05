package Interview.OOP.Controller;

import Interview.OOP.Exception.IncorrectRequest;
import Interview.OOP.Packageservice.Service;
import java.util.Scanner;

public class Controller {
    private static String request;

    {
        request = new Scanner(System.in).nextLine();
        getMethod();
    }

    public static void start() {
        new Controller();
    }

    public void getMethod() {
        String command;
        String arguments = "";
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
                System.out.println(
                        Service.findBookByName(arguments)
                                .orElseThrow(() ->
                                        new IncorrectRequest("TheBookIsEmptyException"))
                );
                break;
            case "find_books":
                Service.findAllBooks()
                        .forEach(System.out::println);
                break;
            case "find_books_price_between":
                Service.findBookWherePriceBetween(arguments)
                        .forEach(System.out::println);
                break;
            case "save_book":
                System.out.println(Service.saveBook(arguments));
                break;
            case "update_book":
                System.out.println(Service.updateBookByID(arguments));
                break;
            case "delete_book":
                Service.deleteBookByName(arguments);
                break;
            case "delete_all":
                Service.deleteAll();
                break;
            default:
                throw new IncorrectRequest("IncorrectRequestException");
        }
    }
}
