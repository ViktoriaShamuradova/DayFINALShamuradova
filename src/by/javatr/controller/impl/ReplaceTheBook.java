package by.javatr.controller.impl;

import by.javatr.bean.Book;
import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.controller.exception.ReplaceTheBookException;
import by.javatr.service.LibraryService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.LibraryServiceImpl;

public class ReplaceTheBook implements CommandExecute {

    private ReplaceTheBook() {
        view= View.getInstance();
        libraryService=LibraryServiceImpl.getInstance();
    }

    private static final ReplaceTheBook instance = new ReplaceTheBook();

    public static ReplaceTheBook getInstance() {
        return instance;
    }

    private View view;
    private LibraryService libraryService;

    @Override
    public String execute() throws ControllerException {
        int idBook = view.enterIdBook();
        String[] booksCharacteristics = view.enterDataBook();
        Book newBookToSet;
        try {
            newBookToSet = libraryService.createNewBook(booksCharacteristics);
            if(libraryService.replaceBookInLibrary(idBook, newBookToSet)) return "Book is replaced";
            else return "Book is not replaced. Please enter correct id";
        } catch (ServiceException s) {
            throw new ControllerException(s);
        }
    }
}
