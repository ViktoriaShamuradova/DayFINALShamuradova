package by.javatr.controller.impl;

import by.javatr.bean.User;
import by.javatr.view.View;
import by.javatr.controller.command.CommandSignInRegistration;
import by.javatr.service.impl.UserServiceImpl;

public class SignIn implements CommandSignInRegistration {
    private SignIn() {
        view = View.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    private static final SignIn instance = new SignIn();

    public static SignIn getInstance() {
        return instance;
    }

    private UserServiceImpl userService;
    private View view;

    @Override
    public User execute() {
        String[] loginAndPassword = view.enterSignIn();
        User user = userService.signIn(loginAndPassword[0], loginAndPassword[1]);
        if (user == null) {
            view.printMessage("Login or password is wrong");
            return null;
        }else return user;
    }
}
