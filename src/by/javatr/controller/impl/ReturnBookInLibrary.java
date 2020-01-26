package by.javatr.controller.impl;

import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.LibraryService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.LibraryServiceImpl;

public class ReturnBookInLibrary implements CommandExecute {
    private ReturnBookInLibrary() {
        view = View.getInstance();
        libraryService = LibraryServiceImpl.getInstance();
    }

    private static final ReturnBookInLibrary instance = new ReturnBookInLibrary();

    public static ReturnBookInLibrary getInstance() {
        return instance;
    }

    private View view;
    private LibraryService libraryService;

    @Override
    public String execute() throws ControllerException{
        String nameBook = view.enterNameBook();
        try {
            if(libraryService.returnBookInLibrary(nameBook)) return "The book was successfully submitted to the library";
            else  return "The book not submit in library";
        } catch (ServiceException s) {
            throw new ControllerException(s);
        }
    }
}
