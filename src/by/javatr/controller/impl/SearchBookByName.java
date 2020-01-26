package by.javatr.controller.impl;

import by.javatr.bean.Book;
import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.LibraryService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.LibraryServiceImpl;

import java.util.Map;

public class SearchBookByName implements CommandExecute {
    private SearchBookByName() {
        view = View.getInstance();
        libraryService = LibraryServiceImpl.getInstance();
    }

    private static final SearchBookByName instance = new SearchBookByName();

    public static SearchBookByName getInstance() {
        return instance;
    }

    private View view;
    private LibraryService libraryService;

    @Override
    public String execute() throws ControllerException {
        String nameBook = view.enterNameBook();
        StringBuilder sb = new StringBuilder();
        Map<Integer, Book> books;
        try {
            books = libraryService.searchBookInLibraryByName(nameBook);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        for (Integer id : books.keySet()) {
            sb.append(books.get(id).getName()).append(" ")
                    .append(books.get(id).getRating()).append(" ")
                    .append(books.get(id).isBestSeller())
                    .append(" ").append(id).append(" ").append("\n");
        }
        return new String(sb);
    }
}
