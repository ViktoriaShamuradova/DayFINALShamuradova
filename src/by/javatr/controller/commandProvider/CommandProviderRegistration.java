package by.javatr.controller.commandProvider;

import by.javatr.controller.command.CommandSignInRegistration;
import by.javatr.controller.impl.RegistrationClient;
import by.javatr.controller.impl.SignIn;

import java.util.HashMap;
import java.util.Map;

public class CommandProviderRegistration {
    private final Map<Integer, CommandSignInRegistration> repository = new HashMap<>();

    public CommandProviderRegistration() {
        repository.put(1, SignIn.getInstance());
        repository.put(2, RegistrationClient.getInstance());
    }

    public CommandSignInRegistration getCommand(int numberCommand) {
        CommandSignInRegistration command = null;
        command = repository.get(numberCommand);
        return command;
    }

}
