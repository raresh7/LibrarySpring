package dao;

import java.util.List;

import entities.Book;

public interface BookService {
    public List<Book> getAvailableBooks();
    public Book getBook(int id);
    public List<Book> getAll();
    public void createBook(Book book);
    public void deleteBook(int id);
    public void updateBook(Book book);
}