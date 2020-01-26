package by.javatr.controller.impl;

import by.javatr.bean.User;
import by.javatr.controller.command.CommandExecute;
import by.javatr.service.impl.UserServiceImpl;

import java.util.List;

public class AllAdmins implements CommandExecute {
    private AllAdmins() {
        userService = UserServiceImpl.getInstance();
    }

    private static final AllAdmins instance = new AllAdmins();

    public static AllAdmins getInstance() {
        return instance;
    }

    private UserServiceImpl userService;

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        List<User> admins = userService.allAdmins();
        for (User user : admins) {
            sb.append(user.getLogin()).append(" ").append("\n");
        }
        return new String(sb);
    }
}
