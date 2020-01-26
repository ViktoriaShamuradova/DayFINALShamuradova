package by.javatr.controller.commandProvider;

import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProviderAdmin {
    private final Map<Integer, CommandExecute> repository = new HashMap<>();

    public CommandProviderAdmin() {
        repository.put(1, AddNewBook.getInstance());
        repository.put(2, ShowAllBooksInLibrary.getInstance());
        repository.put(3, DeleteBookById.getInstance());
        repository.put(4, SearchBookByName.getInstance());
        repository.put(5, SearchBookById.getInstance());
        repository.put(6, ReplaceTheBook.getInstance());
        repository.put(7, RegistrationAdmin.getInstance());
        repository.put(8, AllClients.getInstance());
        repository.put(9, AllAdmins.getInstance());
        repository.put(10, DeleteAdmin.getInstance());
        repository.put(11, ShowAllIssuedBooks.getInstance());
        repository.put(0, WrongCommand.getInstance());
    }

    public CommandExecute getCommand(int numberCommand) {
        CommandExecute command = null;
        try {
            command = repository.get(numberCommand);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(0);
        }
        return command;
    }
}
