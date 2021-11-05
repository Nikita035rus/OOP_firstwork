package Interview.OOP.Entity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Book implements Comparator<Book> {
    private static int count;
    private final int id;
    private  String author;
    private  String name;

    private  int price;

    public static Map<String, Book> library = new HashMap<>();

    public Book(String author, String name, int price) {
        this.id = ++count;
        this.author = author;
        this.name = name;
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static void setCount(int count) {
        Book.count = count;
    }

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '.';
    }

    @Override
    public int compare(Book o1, Book o2) {
        return o1.id - o2.id;
    }
}
