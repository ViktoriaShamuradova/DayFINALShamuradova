package by.javatr.controller.impl;

import by.javatr.bean.Book;
import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.LibraryService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.LibraryServiceImpl;

public class SearchBookById implements CommandExecute {

    private SearchBookById() {
        view = View.getInstance();
        libraryService = LibraryServiceImpl.getInstance();
    }

    public static SearchBookById getInstance() {
        return instance;
    }

    private static final SearchBookById instance = new SearchBookById();
    private View view;
    private LibraryService libraryService;

    @Override
    public String execute() throws ControllerException {
        int idBook = view.enterIdBook();
        Book book;
        StringBuilder sb = new StringBuilder();
        try {
            book = libraryService.searchBookInLibraryById(idBook);
        } catch (ServiceException s) {
            throw new ControllerException(s);
        }
        sb.append(book.getName()).append(" ")
                    .append(book.getRating()).append(" ")
                    .append(book.isBestSeller()).append(" ")
                    .append(book.getIdBook()).append(" ").append("\n");

        return new String(sb);
    }
}
