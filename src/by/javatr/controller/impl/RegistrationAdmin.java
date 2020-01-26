package by.javatr.controller.impl;

import by.javatr.bean.Login;
import by.javatr.bean.Password;
import by.javatr.view.View;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.controller.valid.ValidLogin;
import by.javatr.controller.valid.ValidPassword;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.UserServiceImpl;

public class RegistrationAdmin implements CommandExecute {
    private RegistrationAdmin() {
        userService=UserServiceImpl.getInstance();
        view=View.getInstance();
    }

    public static RegistrationAdmin getInstance() {
        return instance;
    }

    private static final RegistrationAdmin instance = new RegistrationAdmin();

    private View view;
    private UserServiceImpl userService ;
    private ValidPassword validPassword = ValidPassword.getInstance();
    private ValidLogin validLogin = ValidLogin.getInstance();

    @Override
    public String execute() throws ControllerException {
        String newLogin = view.enterLogin();
        Login login = new Login(newLogin);
            if(validLogin.isValidLogin(login)) {
                if (userService.isThereSuchLogin(login)) return "Such login is already in use";
                String[] passwords = view.enterNewPassword();
                if(passwords[0].equals(passwords[1])) {
                    Password password = new Password(passwords[0]);
                    if (validPassword.isValidPassword(password)) {
                        try {
                            userService.createAccountAdmin(login, password);
                        } catch (ServiceException s) {
                            throw new ControllerException(s);
                        } return ("Account created successfully");
                    } return "Password are not valid. Please, try to register again";
                }return "Passwords are not equals. Try again";
            } return "Please, enter valid login";
    }
}
