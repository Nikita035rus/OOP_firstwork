package Interview.OOP.Packageservice;


import Interview.OOP.Entity.Book;
import Interview.OOP.Exception.IncorrectRequest;
import Interview.OOP.Repository.Repository;

import java.util.List;
import java.util.Optional;

public class Service {
    public static Optional<Book> findBookByName(String name) {
        //find_book?name=Java8
        if (!name.contains("name=")) {
            throw new IncorrectRequest("IncorrectArgumentsException");
        }
        String nameBook = name.substring(name.indexOf("=") + 1)
                .replace('_', ' ');

        return Repository.findBookByName(nameBook);
    }

    public static List<Book> findAllBooks() {
        return Repository.findAllBooks();
    }

    public static List<Book> findBookWherePriceBetween(String arguments) {
        //find_books_price_between?from=100&to=200

        if (!arguments.contains("from=")
                && !arguments.contains("to=")) {
            throw new IncorrectRequest("IncorrectArgumentsException");
        }

        String[] arrayArguments = arguments
                .substring(arguments.indexOf("?") + 1)
                .split("&");


        int from;
        int to;
        try {
            from = Integer.parseInt(arrayArguments[0]
                    .substring(arrayArguments[0].indexOf("=") + 1));
            to = Integer.parseInt(arrayArguments[1]
                    .substring(arrayArguments[1].indexOf("=") + 1));
            if (from < 0 || to < 0 || from > to) {
                throw new IncorrectRequest("IncorrectArgumentException");
            }
        } catch (NumberFormatException exception) {
            throw new IncorrectRequest("IncorrectArgumentException");
        }

        return Repository.findBookWherePriceBetween(from, to);
    }

    public static Book saveBook(String arguments) {
        //author=Schildt&name=Programming_on_Java&cost=100

        if (!arguments.contains("author=")
                && !arguments.contains("name=")
                && !arguments.contains("cost=")) {
            throw new IncorrectRequest("IncorrectArgumentsException");
        }

        String[] arrayArguments = (arguments.replace('_', ' ')).split("&");
        String author = arrayArguments[0]
                .substring(arrayArguments[0].indexOf("=") + 1);

        String name = arrayArguments[1]
                .substring(arrayArguments[1].indexOf("="));
        int cost;
        try {
            cost = Integer.parseInt(arrayArguments[2].substring(arrayArguments[2].indexOf("=") + 1));
        } catch (NumberFormatException exception) {
            throw new IncorrectRequest("IncorrectCostException");
        }
        Book book = new Book(author, name, cost);
        return Repository.saveBook(book);
    }

    public static Book updateBookByID(String arguments) {
    //update_book?id=1&author=Artur&name=Java&price=112
        String [] arrayBooks = arguments
                .substring(arguments.indexOf("?")+1)
                .replace('_', ' ')
                .split("&");
        String author = arrayBooks[1]
                .substring(arrayBooks[1].indexOf("=") + 1);

        String name = arrayBooks[2]
                .substring(arrayBooks[2].indexOf("="));
        int id;
        int coast;
        try {
            id = Integer.parseInt(arrayBooks[0].substring(arrayBooks[0].indexOf("=") + 1));
            coast = Integer.parseInt(arrayBooks[3].substring(arrayBooks[3].indexOf("=") + 1));
        }catch (NumberFormatException exception){
            throw new IncorrectRequest("IncorrectIdException");
        }
        return Repository.updateBookByID(id, new Book(author,name,coast));
    }

    public static void deleteBookByName(String name) {
        //delete_book?name=Java
        String nameBook= name.substring(name.indexOf("=")+1)
                .replace('_', ' ');
        Repository.deleteBookByName(nameBook);
    }

    public static void deleteAll() {
        //delete_all
        Repository.deleteAll();
    }
}
