package by.javatr.controller.impl;

import by.javatr.bean.Book;
import by.javatr.controller.command.CommandExecute;
import by.javatr.service.LibraryService;
import by.javatr.service.impl.LibraryServiceImpl;

import java.util.NavigableSet;

public class ShowBibliography implements CommandExecute {

    private ShowBibliography() {
        libraryService=LibraryServiceImpl.getInstance();
    }

    private static final ShowBibliography instance = new ShowBibliography();

    public static ShowBibliography getInstance() {
        return instance;
    }

    private LibraryService libraryService;

    @Override
    public String execute(){
        StringBuilder sb = new StringBuilder();

            NavigableSet<Book> bookSet = libraryService.returnUnicBooks();
                for (Book book : bookSet) {
                    sb.append(book.getName()).append(" ")
                            .append(book.getRating()).append(" ")
                            .append(book.isBestSeller()).append("\n");
                }
        return new String(sb);
    }
}
