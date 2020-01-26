package by.javatr.dao;

import by.javatr.bean.Book;
import by.javatr.bean.Login;
import by.javatr.bean.Password;
import by.javatr.bean.User;
import by.javatr.dao.exception.DaoException;
import by.javatr.dao.exception.WriterException;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    private Writer() {
    }

    private static final Writer instance = new Writer();

    public static Writer getInstance() {
        return instance;
    }

    public void writeUsersToFile(List<User> listOfUsers, File fileName) throws WriterException {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(String.valueOf(fileName))))) { //ObjectOutPutStream умеет сериализовать объекты, но ему неизвесто, куда мы собираемся отправлять эти объекты(по сети или сохранить в файл, поэтому должны указать внутренний стрим, который мы будем использовать для сохранения наших объетов, то есть ObjectOutPutStream обернет переданный стрим в конструктор и используя его выполнит сериализацию и отправку, также этот класс отвечает за проверку того, что наш объект сериализуемый
            for (User user : listOfUsers) {                                                                                  // то есть создаем стрим для сохранения объетокв, для этого понадобится класс Files.newOutputStream возвращает OutputStream, а ему в свою очередь понадобятся координаты нашего файла, как минимум нужно указать имя файла
                out.writeObject(user);
            }
            out.writeObject(new User(new Login("1111"), new Password("11111111"), "admin"));
        } catch (IOException e) {
            throw new WriterException("File cannot be open.");
        }
    }

    public void writeBooksToFile(List<Book> listOfBooks, File fileName) throws WriterException {                                                      //записывем объекты в бинарные файлы, то есть они не текстовые и читать их с помощбю текстового редактора невозможно, будет аброкодабра, чтобы сохранить объект, он должен быть сериализиремый
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(String.valueOf(fileName))))) { //ObjectOutPutStream умеет сериализовать объекты, но ему неизвесто, куда мы собираемся отправлять эти объекты(по сети или сохранить в файл, поэтому должны указать внутренний стрим, который мы будем использовать для сохранения наших объетов, то есть ObjectOutPutStream обернет переданный стрим в конструктор и используя его выполнит сериализацию и отправку, также этот класс отвечает за проверку того, что наш объект сериализуемый
            for (Book book : listOfBooks) {                                                                                  // то есть создаем стрим для сохранения объетокв, для этого понадобится класс Files.newOutputStream возвращает OutputStream, а ему в свою очередь понадобятся координаты нашего файла, как минимум нужно указать имя файла
                out.writeObject(book);
            }
            out.writeObject(new Book("1111", 0, false));
        } catch (IOException e) {
            throw new WriterException("File cannot be open.");
        }
    }

    public void writeBookToFile(Book book, File fileName) throws WriterException {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(String.valueOf(fileName))))) {
            out.writeObject(book);
            out.writeObject(new Book("1111", 0, false));
        } catch (IOException e) {
           throw new WriterException("File cannot be open");
        }
    }


    public static void main(String[] args) throws DaoException, WriterException {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Философия JAVA", 5.3f, false));
        books.add(new Book("Изучаем JAVA", 7.3f, true));
        books.add(new Book("Кулинария", 3.6f, false));
        books.add(new Book("Эволюция человечества", 9.3f, true));
        books.add(new Book("Познер", 7.5f, false));
        books.add(new Book("Поющие в терновнике", 6.6f, false));
        books.add(new Book("Приключения кротика", 7.1f, false));
        books.add(new Book("Я буду ждать тебя всегда", 8.5f, false));
        books.add(new Book("Тонкое искусство пофигизма", 9.4f, true));
        books.add(new Book("Война и мир", 7.3f, false));
        File file = new File("library.bin");
        File file2 = new File("Issued books.bin");
        List<Book> issued = new ArrayList();

        Book book1 = new Book("Грызун с большой дороги", 8.6f, false);
        Book book2 = new Book("Груффало", 9.6f, true);
        issued.add(book1);
        issued.add(book2);
        Writer writer = Writer.getInstance();
        writer.writeBooksToFile(books, file);
        writer.writeBooksToFile(issued, file2);
        Reader reader = Reader.getInstance();
        List<Book> books1 = reader.readBooksFromFile(file);
        System.out.println(books1);
        System.out.println(reader.readBooksFromFile(file2));

    }
}
    //    public void writeUsersToFile(File fileOfUsers) {
//
//        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(String.valueOf(fileOfUsers))))) { //ObjectOutPutStream умеет сериализовать объекты, но ему неизвесто, куда мы собираемся отправлять эти объекты(по сети или сохранить в файл, поэтому должны указать внутренний стрим, который мы будем использовать для сохранения наших объетов, то есть ObjectOutPutStream обернет переданный стрим в конструктор и используя его выполнит сериализацию и отправку, также этот класс отвечает за проверку того, что наш объект сериализуемый
//            // то есть создаем стрим для сохранения объетокв, для этого понадобится класс Files.newOutputStream возвращает OutputStream, а ему в свою очередь понадобятся координаты нашего файла, как минимум нужно указать имя файла
//            out.writeObject(new User(new Login("Vika"), new Password("1234567a"), "admin"));
//
//            out.writeObject(new User(new Login("1111"), new Password("11111111"), "admin"));
//        } catch (IOException e) {
//            System.out.println("File cannot be open. Program terminates");
//            e.printStackTrace();
//        }
//    }


