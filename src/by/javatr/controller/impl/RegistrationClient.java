package by.javatr.controller.impl;

import by.javatr.bean.Login;
import by.javatr.bean.Password;
import by.javatr.bean.User;
import by.javatr.view.View;
import by.javatr.controller.command.CommandSignInRegistration;
import by.javatr.controller.exception.ControllerException;
import by.javatr.controller.valid.ValidLogin;
import by.javatr.controller.valid.ValidPassword;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.impl.UserServiceImpl;

public class RegistrationClient implements CommandSignInRegistration {

    private RegistrationClient() {
        view=View.getInstance();
        userService=UserServiceImpl.getInstance();
        validLogin=ValidLogin.getInstance();
        validPassword= ValidPassword.getInstance();
    }

    private static final RegistrationClient instance = new RegistrationClient();

    public static RegistrationClient getInstance() {
        return instance;
    }

    private View view;
    private UserServiceImpl userService;
    private ValidPassword validPassword;
    private ValidLogin validLogin;

    @Override
    public User execute() throws ControllerException {
        User newAccount;
        String loginStr = view.enterLogin();
        Login newLogin = new Login(loginStr);
        if (!userService.isThereSuchLogin(newLogin)) {
                if(validLogin.isValidLogin(newLogin)) {
                    String[] passwords = view.enterNewPassword();
                    if (passwords[0].equals(passwords[1])) {
                        Password password = new Password(passwords[0]);
                        if(validPassword.isValidPassword(password)) {
                            try {
                                newAccount = userService.createAccountClient(newLogin, password);
                                view.printMessage("Account created successfully");
                                return newAccount;
                            }catch (ServiceException s){
                                throw new ControllerException(s);
                            }
                        }
                        view.printMessage("Password is not valid. Please, try again");
                        return null;
                    }
                    view.printMessage("Passwords are not equals, please, try again");
                    return null;
                }
                view.printMessage("Login is not valid. Please, try again");
                return null;

        } else {
            view.printMessage("This login is already in use");
            return null;
        }
    }
}
