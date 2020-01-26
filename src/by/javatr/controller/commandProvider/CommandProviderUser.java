package by.javatr.controller.commandProvider;

import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProviderUser {
    private final Map<Integer, CommandExecute> repository = new HashMap<>();

    public CommandProviderUser() {
        repository.put(1, TakeBookForReading.getInstance());
        repository.put(2, ReturnBookInLibrary.getInstance());
        repository.put(3, SearchBookByName.getInstance());
        repository.put(4, ShowBibliography.getInstance());
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
