package by.javatr.service.impl;

import by.javatr.bean.Book;
import by.javatr.controller.valid.ValidBook;
import by.javatr.dao.LibraryDao;
import by.javatr.dao.exception.DaoException;
import by.javatr.dao.impl.LibraryDaoImpl;
import by.javatr.service.LibraryService;
import by.javatr.service.exception.ServiceException;

import java.util.*;

public class LibraryServiceImpl implements LibraryService {

    private LibraryServiceImpl() {
        libraryDao = LibraryDaoImpl.getInstance();
        validBook = ValidBook.getInstance();
    }

    private static final LibraryServiceImpl instance = new LibraryServiceImpl();

    public static LibraryServiceImpl getInstance() {
        return instance;
    }

    private ValidBook validBook;
    private LibraryDao libraryDao;

    @Override
    public boolean addNewBookInLibrary(Book book) throws ServiceException {
        try {
            if (libraryDao.saveBookInLibrary(book)) return true;
            return false;
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
    }

    @Override
    public List<Book> allBooksInLibrary() {
        List<Book> allBooks;
        allBooks = libraryDao.returnAllBookFromLibrary();
        Collections.sort(allBooks);
        return allBooks;
    }

    @Override
    public List<Book> allBooksInIssuedList() {
        List<Book> allBooks;
        allBooks = libraryDao.returnAllBookFromIssuedList();
        Collections.sort(allBooks);
        return allBooks;
    }

    @Override
    public boolean deleteBookFromLibraryById(int id) throws ServiceException {
        try {
            List<Book> books = libraryDao.returnAllBookFromLibrary();
            Collections.sort(books);
            if (id < 0 || id > books.size()) return false;
            Iterator<Book> bookIterator = books.iterator();
            while (bookIterator.hasNext()) {
                Book book = bookIterator.next();
                if (books.indexOf(book) == id) {
                    bookIterator.remove();
                    libraryDao.saveArrayLibrary(books);
                    return true;
                }
            }
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
        return false;
    }

    @Override
    public Book createNewBook(String[] dataBook) {
        float rating = Float.parseFloat(dataBook[1]);
        boolean isBestSeller = Boolean.parseBoolean(dataBook[2]);
        Book book = new Book(dataBook[0], rating, isBestSeller);
        if (validBook.isValidBook(book)) {
            return book;
        } else return null;

    }

    @Override
    public boolean replaceBookInLibrary(int idBook, Book newBook) throws ServiceException {
        try {
            List<Book> books = libraryDao.returnAllBookFromLibrary();
            books.remove(idBook);
            books.add(newBook);
            libraryDao.saveArrayLibrary(books);
            return true;
        } catch (IndexOutOfBoundsException d) {
            return false;
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
    }

    @Override
    public boolean takeBookForReading(int idBook) throws ServiceException {
        Book book = searchBookInLibraryById(idBook);
        if (book == null) return false;
        try {
            libraryDao.deleteBookFromLibrary(book);
            libraryDao.saveBookInIssued(book);
            boolean haveBook = false;
            List<Book> books1 = libraryDao.returnAllBookFromLibrary();
            Iterator<Book> iterator = books1.iterator();
            while (iterator.hasNext()) {
                Book next = iterator.next();
                if (next.equals(book)) {
                    haveBook = true;
                    break;
                }
            }
            if(!haveBook) {
                NavigableSet<Book> books = libraryDao.returnUnicBooks();
                for (Book book1 : books) {
                    if (book1.getName().equals(book.getName()) && book1.getRating() == book.getRating() && book1.isBestSeller() == book.isBestSeller()) {
                        books.remove(book1);
                        break;
                    }
                }
                libraryDao.saveListOfLiterature(books);
            }
            return true;
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
    }

    @Override
    public boolean returnBookInLibrary(String nameBook) throws ServiceException {
        Book returnBook = null;
        List<Book> books = libraryDao.returnAllBookFromIssuedList();
        boolean bookHave = false;
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(nameBook)) {
                returnBook = new Book(book.getName(), book.getRating(), book.isBestSeller());
                bookHave = true;
                break;
            }
        }
        //удаляем книгу из списка выданных книг
        if (bookHave) {
            Iterator<Book> iterator = books.iterator();
            while (iterator.hasNext()) {
                Book nextBook = iterator.next();
                if (nextBook.getName().equalsIgnoreCase(nameBook)) {
                    iterator.remove();
                    break;
                }
            }
            try {
                libraryDao.saveArrayInIssued(books);
                libraryDao.saveBookInLibrary(returnBook);
            } catch (DaoException d) {
                throw new ServiceException(d);
            }
            //значит по названию книгу не нашли в списке выданных книг
        }
        return bookHave;
    }

    @Override
    public Map<Integer, Book> searchBookInLibraryByName(String name) {
        Map<Integer, Book> books = new HashMap<>();
        List<Book> allBooks = libraryDao.returnAllBookFromLibrary();
        for (Book book : allBooks) {
            if (book.getName().equals(name)) {
                books.put(allBooks.indexOf(book), book);
            }
        }
        return books;
    }

    @Override
    public Book searchBookInLibraryById(int id) {
        try {
            List<Book> books = libraryDao.returnAllBookFromLibrary();
            Collections.sort(books);
            Book book = books.get(id);
            book.setIdBook(id);
            return book;
        } catch (IndexOutOfBoundsException i) {
            return null;
        }
    }

    @Override
    public NavigableSet<Book> returnUnicBooks() {
        return libraryDao.returnUnicBooks();
    }
}
