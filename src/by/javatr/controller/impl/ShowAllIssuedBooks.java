package by.javatr.controller.impl;

import by.javatr.bean.Book;
import by.javatr.controller.command.CommandExecute;
import by.javatr.service.LibraryService;
import by.javatr.service.impl.LibraryServiceImpl;

import java.util.List;

public class ShowAllIssuedBooks implements CommandExecute {
    private ShowAllIssuedBooks() {
        libraryService=LibraryServiceImpl.getInstance();
    }

    private static final ShowAllIssuedBooks instance = new ShowAllIssuedBooks();

    public static ShowAllIssuedBooks getInstance() {
        return instance;
    }

    private LibraryService libraryService;

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        List<Book> books = libraryService.allBooksInIssuedList();
        for (Book book : books) {
            sb.append(books.indexOf(book)).append(" ")
                    .append(book.getName()).append(" ")
                    .append(book.getRating()).append(" ")
                    .append(book.isBestSeller()).append(" ").append("\n");
        }
        return new String(sb);
    }
}
