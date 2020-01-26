package by.javatr.controller.valid;

import by.javatr.bean.Password;

public class ValidPassword {
    private ValidPassword() {
    }

    private static final ValidPassword instance = new ValidPassword();

    public static ValidPassword getInstance() {
        return instance;
    }

    public boolean isValidPassword(Password password) {
        if (password.getPassword() == null ||
                password.getPassword().equals(" ") ||
                password.getPassword().equals("") ||
                password.getPassword().length() < 7) {
            return false;
        }
        return true;
    }
}
