package by.javatr.controller.command;

import by.javatr.bean.User;
import by.javatr.controller.exception.ControllerException;

public interface CommandSignInRegistration extends Command {
    User execute() throws ControllerException;
}
