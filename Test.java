package Interview.oop;

import Interview.oop.controller.BookController;

public class Test {
    public static void main(String[] args) {
       /* Book.library.put("Java", new Book("Brian", "Java", 100));
        Book.library.put("Java8", new Book("Jon", "Java8", 10));
        Book.library.put("Java9", new Book("Jessica", "Java9", 200));
        Book.library.put("Java10", new Book("Steve", "Java9", 150));*/
        BookController.start();
    }
}


