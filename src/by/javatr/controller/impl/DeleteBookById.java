package by.javatr.controller.impl;

import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.controller.exception.DeleteBookControllerException;
import by.javatr.service.LibraryService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.LibraryServiceImpl;

public class DeleteBookById implements CommandExecute {

    private DeleteBookById() {
        view = View.getInstance();
        libraryService = LibraryServiceImpl.getInstance();
    }

    private static final DeleteBookById instance = new DeleteBookById();

    public static DeleteBookById getInstance() {
        return instance;
    }

    private View view;
    private LibraryService libraryService;

    @Override
    public String execute() throws ControllerException {
        int idBook = view.enterIdBook();
        try {
            if (libraryService.deleteBookFromLibraryById(idBook)) return "Book deleted by id";
            return "Book id not delete. Id does not match";
        } catch (ServiceException s) {
            throw new ControllerException();
        }
    }
}
