package by.javatr.dao;

import by.javatr.bean.Book;
import by.javatr.bean.Login;
import by.javatr.bean.User;
import by.javatr.dao.exception.DaoException;
import by.javatr.dao.exception.ReaderException;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private Reader() {
    }

    private static final Reader instance = new Reader();

    public static Reader getInstance() {
        return instance;
    }

    public List<User> readUsersFromFile(File fileName) throws ReaderException {
        List<User> listOfUsers = new ArrayList<>();
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(String.valueOf(fileName))))) {//указывем из какого стрима мы собираемся читать данные
            boolean keepReading = true;                      //ObjectInputStream не знает, какой тип нужно возвращать и он всегда использует обжек
            while (keepReading) {                               //возможно появится ClassNotFoundEx. ObjectInputStream не знает, когда заканчивается файл
                User user = (User) input.readObject();                                            //пока keepReading true, выполняем чтение из файла
                if (!user.getLogin().equals(new Login("1111"))) {
                    listOfUsers.add(user);
                } else {
                    keepReading = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new ReaderException(e);
        }
        return listOfUsers;
    }

    public List<Book> readBooksFromFile(File fileName) throws ReaderException { ////////!!!!!!!!!
        List<Book> listOfBooks = new ArrayList<>();
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(String.valueOf(fileName))));) {//указывем из какого стрима мы собираемся читать данные
            boolean keepReading = true;                      //ObjectInputStream не знает, какой тип нужно возвращать и он всегда использует обжек
            while (keepReading) {                               //возможно появится ClassNotFoundEx. ObjectInputStream не знает, когда заканчивается файл
                Book book = (Book) input.readObject();                                            //пока keepReading true, выполняем чтение из файла
                if (!book.getName().equals("1111")) {
                    listOfBooks.add(book);
                } else {
                    keepReading = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new ReaderException(e);
        }
        return listOfBooks;
    }
}
