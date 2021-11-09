package Interview.oop.repository;


import Interview.oop.entity.Book;
import Interview.oop.exception.IncorrectRequest;

import java.util.*;
import java.util.stream.Collectors;

public class BookRepository {
    public static Set<Book> library = new HashSet<>();

    public Optional<Book> findBookByName(String name) {
        return library
                .stream()
                .filter((x) -> x.getName().equals(name))
                .findFirst();
    }

    public List<Book> findAllBooks() {
        if (library.size() == 0) {
            throw new IncorrectRequest("TheLibraryIsEmptyException");
        }
        return new ArrayList<>(library);
    }

    public List<Book> findBookWherePriceBetween(int from, int to) {
        return library
                .stream()
                .filter((x) -> (x.getPrice() >= from && x.getPrice() <= to))
                .collect(Collectors.toList());
    }

    public Book saveBook(Book book) {
        if (library.contains(book)) {
            throw new IncorrectRequest("ThisBookIsAlreadyContainedInTheLibrary");
        }
        library.add(book);
        return book;
    }

    public Book updateBookByID(int id, Book newBook) {

        Optional<Book> optionalBook =library
                .stream()
                .filter((x) -> x.getId() == id)
                .findFirst()
                .map((x) -> {
                            x.setAuthor(newBook.getAuthor());
                            x.setName(newBook.getName());
                            x.setPrice(newBook.getPrice());
                        return x;});
        return optionalBook
                .orElseThrow(()->new IncorrectRequest("IdIsEmptyException"));
    }

    public void deleteBookByName(String name) {
        Book book = library
                .stream()
                .filter((x)->x.getName().equals(name))
                .findFirst()
                .orElseThrow(()->new IncorrectRequest("BookIsEmptyException"));
        library.remove(book);
        System.out.println("Success");
    }

    public void deleteAll() {
        if(library.size()==0){
            throw new IncorrectRequest("LibraryIsEmptyException");
        }
        System.out.println(library.size()+" books deleted");
        library.clear();
        Book.setCount(0);
    }
}
