package Interview.oop.entity;

import java.util.Objects;

public class Book {
    private static int count;
    private final int id;
    private  String author;
    private  String name;

    private  int price;


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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && price == book.price && Objects.equals(author, book.author) && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, name, price);
    }
}
