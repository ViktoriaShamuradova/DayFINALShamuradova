package by.javatr.dao;

import by.javatr.bean.Book;
import by.javatr.dao.exception.DaoException;

import java.util.List;
import java.util.NavigableSet;


public interface LibraryDao {

    boolean saveBookInLibrary(Book book) throws DaoException;

    List<Book> returnAllBookFromLibrary() throws DaoException;
    List<Book> returnAllBookFromIssuedList() throws DaoException;

    void deleteBookFromLibrary(Book book)throws DaoException;

    void saveArrayLibrary(List<Book> books) throws DaoException;

    NavigableSet<Book> returnUnicBooks()throws DaoException;

    void saveBookInIssued(Book book)throws DaoException;

    void saveArrayInIssued(List<Book> books) throws DaoException;

    void saveListOfLiterature(NavigableSet<Book> books);
}
