package by.javatr.controller.impl;

import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.LibraryService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.LibraryServiceImpl;

public class TakeBookForReading implements CommandExecute {
    private TakeBookForReading() {
        view = View.getInstance();
        libraryService = LibraryServiceImpl.getInstance();
    }

    private static final TakeBookForReading instance = new TakeBookForReading();

    public static TakeBookForReading getInstance() {
        return instance;
    }

    private View view;
    private LibraryService libraryService;

    @Override
    public String execute() throws ControllerException {
        ShowAllBooksInLibrary showBooks = ShowAllBooksInLibrary.getInstance();
        view.printMessage(showBooks.execute());
        SearchBookByName searchBooks = SearchBookByName.getInstance();
        view.printMessage(searchBooks.execute());
        int idBook = view.enterIdBookTakeForReading();
        try {
            libraryService.takeBookForReading(idBook);
        } catch (ServiceException s) {
            throw new ControllerException(s);
        }
        return "Your book is ready. Enjoy reading!";
    }
}
