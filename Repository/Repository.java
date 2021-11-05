package Interview.OOP.Repository;


import Interview.OOP.Entity.Book;
import Interview.OOP.Exception.IncorrectRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Repository {

    public static Optional<Book> findBookByName(String name) {

        //.orElseThrow(new IncorrectRequest("TheBookIsEmptyException"));
        return Book.library
                .values()
                .stream()
                .filter((x) -> x.getName().equals(name))
                .findFirst();
    }

    public static List<Book> findAllBooks() {
        if (Book.library.size() == 0) {
            throw new IncorrectRequest("TheLibraryIsEmptyException");
        }
        List<Book> bookList = new ArrayList<>(Book.library.values());

        bookList.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));
        /* по 138 строчке вопрос, среда выделяет серым цветом,
         * но если убрать, то сортировка перестает работать*/

        return bookList;
    }

    public static List<Book> findBookWherePriceBetween(int from, int to) {

        return Book.library.values()
                .stream()
                .filter((x) -> (x.getPrice() >= from && x.getPrice() <= to))
                .collect(Collectors.toList());
    }

    public static Book saveBook(Book book) {
        if (Book.library.containsKey(book.getName())) {
            throw new IncorrectRequest("ThisBookIsAlreadyContainedInTheLibrary");
        }
        Book.library.put(book.getName(), book);
        return book;
    }

    public static Book updateBookByID(int id, Book newBook) {

        Optional<Book> optionalBook =Book.library
                .values()
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

    public static void deleteBookByName(String name) {
        if(!Book.library.keySet().remove(name)){
            throw new IncorrectRequest("BookIsEmptyException");
        } else {
            System.out.println("Success");
        }
    }

    public static void deleteAll() {
        if(Book.library.size()==0){
            throw new IncorrectRequest("LibraryIsEmptyException");
        }
        System.out.println(Book.library.size()+" books deleted");
        Book.library.clear();
        Book.setCount(0);
    }
}
