package Interview.oop.packageservice;


import Interview.oop.entity.Book;
import Interview.oop.exception.IncorrectRequest;
import Interview.oop.repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findBookByName(String name) {
        //find_book?name=Java8
        if (!name.contains("name=")) {
            throw new IncorrectRequest("IncorrectArgumentsException");
        }
        String nameBook = name.substring(name.indexOf("=") + 1)
                .replace('_', ' ');

        return bookRepository.findBookByName(nameBook);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }

    public List<Book> findBookWherePriceBetween(String arguments) {
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
                throw new IncorrectRequest("IncorrectIntervalException");
            }
        } catch (NumberFormatException exception) {
            throw new IncorrectRequest("ArgumentIsNonNumericException");
        }

        return bookRepository.findBookWherePriceBetween(from, to);
    }

    public Book saveBook(String arguments) {
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
        return bookRepository.saveBook(book);
    }

    public Book updateBookByID(String arguments) {
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
        return bookRepository.updateBookByID(id, new Book(author,name,coast));
    }

    public void deleteBookByName(String name) {
        //delete_book?name=Java
        String nameBook= name.substring(name.indexOf("=")+1)
                .replace('_', ' ');
        bookRepository.deleteBookByName(nameBook);
    }

    public void deleteAll() {
        //delete_all
        bookRepository.deleteAll();
    }
}
