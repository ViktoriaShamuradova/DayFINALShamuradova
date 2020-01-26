package by.javatr.controller.impl;

import by.javatr.bean.User;
import by.javatr.controller.command.CommandExecute;
import by.javatr.service.impl.UserServiceImpl;

import java.util.List;

public class AllClients implements CommandExecute {
    private AllClients() {
        userService = UserServiceImpl.getInstance();
    }

    private static final AllClients instance = new AllClients();

    public static AllClients getInstance() {
        return instance;
    }

    private UserServiceImpl userService;

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        List<User> clients = userService.allClients();
        for (User user : clients) {
            sb.append(user.getLogin()).append(" ").append("\n");
        }
        return new String(sb);
    }
}
