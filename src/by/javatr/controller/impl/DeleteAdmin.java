package by.javatr.controller.impl;

import by.javatr.bean.Login;
import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.service.impl.UserServiceImpl;

public class DeleteAdmin implements CommandExecute {
    private DeleteAdmin() {
        userService=UserServiceImpl.getInstance();
        view=View.getInstance();
    }

    private static final DeleteAdmin instance = new DeleteAdmin();

    public static DeleteAdmin getInstance() {
        return instance;
    }

    private View view;
    private UserServiceImpl userService;

    @Override
    public String execute() {
        String loginStr = view.enterAdminLogin();
        Login login = new Login(loginStr);
        userService.deleteUser(login);
        return "well removed";
    }
}
