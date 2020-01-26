package by.javatr.controller.impl;

import by.javatr.bean.Book;
import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.LibraryServiceImpl;


public class AddNewBook implements CommandExecute {
    private AddNewBook() {
        libraryService = LibraryServiceImpl.getInstance();
        view = View.getInstance();
    }

    private static final AddNewBook instance = new AddNewBook();

    public static AddNewBook getInstance() {
        return instance;
    }

    private LibraryServiceImpl libraryService;
    private View view;

    @Override
    public String execute() throws ControllerException {
        String[] booksCharacteristics = view.enterDataBook();
        Book newBook;
        newBook = libraryService.createNewBook(booksCharacteristics);
        try {
            if(libraryService.addNewBookInLibrary(newBook)) return "Book is added";
            return "Book is not added in library. Please, enter valid date of book";
        }catch (ServiceException s){
            throw new ControllerException(s);
        }
    }
}
