package by.javatr.dao.impl;

import by.javatr.bean.Book;
import by.javatr.dao.LibraryDao;
import by.javatr.dao.Reader;
import by.javatr.dao.Writer;
import by.javatr.dao.exception.DaoException;
import by.javatr.dao.exception.ReaderException;
import by.javatr.dao.exception.WriterException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class LibraryDaoImpl implements LibraryDao {

    private LibraryDaoImpl() {
        writer = Writer.getInstance();
        Reader reader = Reader.getInstance();
        libraryFile = new File("library.bin");
        fileOfIssuedBooks = new File("Issued books.bin");
        listOfLiterature = new TreeSet<>();
        try {
            library = reader.readBooksFromFile(libraryFile);
        } catch (ReaderException e) {
            throw new DaoException(e);
        }
        listOfLiterature.addAll(library);
        try {
            issuedBooks = reader.readBooksFromFile(fileOfIssuedBooks);
        } catch (ReaderException e) {
            throw new DaoException(e);
        }
    }

    private static final LibraryDaoImpl instance = new LibraryDaoImpl();

    public static LibraryDaoImpl getInstance() {
        return instance;
    }

    private File libraryFile;
    private File fileOfIssuedBooks;
    private Writer writer;
    private List<Book> library;
    private List<Book> issuedBooks;
    private NavigableSet<Book> listOfLiterature;

    @Override
    public boolean saveBookInLibrary(Book book) throws DaoException {
        if(book==null)return false;
        library.add(book);
        listOfLiterature.add(book);
        saveListOfBooksToFile(library, libraryFile);
        return true;
    }

    @Override
    public void saveArrayLibrary(List<Book> books) throws DaoException {
        library = books;
        saveListOfBooksToFile(library, libraryFile);
    }

    @Override
    public void saveBookInIssued(Book book) throws DaoException {
        issuedBooks.add(book);
        saveListOfBooksToFile(issuedBooks, fileOfIssuedBooks);
    }

    @Override
    public void saveArrayInIssued(List<Book> books) throws DaoException {
        issuedBooks = books;
        saveListOfBooksToFile(issuedBooks, fileOfIssuedBooks);
    }

    @Override
    public void saveListOfLiterature(NavigableSet<Book> books) {
        listOfLiterature=books;
    }

    private void saveListOfBooksToFile(List<Book> books, File file) throws DaoException {
        try {
            writer.writeBooksToFile(books, file);
        } catch (WriterException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> returnAllBookFromLibrary() {
        return new ArrayList<>(library);
    }

    @Override
    public List<Book> returnAllBookFromIssuedList() {
        return new ArrayList<>(issuedBooks);
    }

    @Override
    public void deleteBookFromLibrary(Book book) throws DaoException {
        library.remove(book);
        saveListOfBooksToFile(library, libraryFile);
    }

    @Override
    public NavigableSet<Book> returnUnicBooks() {
        return new TreeSet<>(listOfLiterature);
    }
}
