package by.javatr.controller.valid;

import by.javatr.bean.Login;

public class ValidLogin {
    private ValidLogin(){}
    private static final ValidLogin instance = new ValidLogin();
    public static ValidLogin getInstance(){
        return instance;
    }

    public boolean isValidLogin(Login login) {
        if(login.getLogin() == null ||login.getLogin().equals(" ")|| login.getLogin().equals("")) return false;
        return true;
    }
}
