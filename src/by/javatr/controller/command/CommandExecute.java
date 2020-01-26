package by.javatr.controller.command;

import by.javatr.controller.exception.ControllerException;

public interface CommandExecute extends Command {

    String execute() throws ControllerException;
}
