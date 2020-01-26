package by.javatr.controller.impl;


import by.javatr.controller.command.CommandExecute;

public class WrongCommand implements CommandExecute {
    private WrongCommand() {
    }

    private static final WrongCommand instance = new WrongCommand();

    public static WrongCommand getInstance() {
        return instance;
    }

    @Override
    public String execute()  {
        return "Wrong command, please, try again";
    }
}
