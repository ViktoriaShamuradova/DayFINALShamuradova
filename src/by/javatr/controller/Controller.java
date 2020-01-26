package by.javatr.controller;

import by.javatr.bean.User;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.command.CommandSignInRegistration;
import by.javatr.controller.commandProvider.CommandProviderAdmin;
import by.javatr.controller.commandProvider.CommandProviderRegistration;
import by.javatr.controller.commandProvider.CommandProviderUser;
import by.javatr.controller.exception.ControllerException;
import by.javatr.view.View;


public class Controller {
    private Controller() {
        view = View.getInstance();
        providerAdmin = new CommandProviderAdmin();
        providerUser = new CommandProviderUser();
        providerRegistration = new CommandProviderRegistration();
    }

    private static final Controller instance = new Controller();

    public static Controller getInstance() {
        return instance;
    }

    private View view;
    private User user;

    private final CommandProviderAdmin providerAdmin;
    private final CommandProviderUser providerUser;
    private final CommandProviderRegistration providerRegistration;

    public String executeEnterOrRegistration(int request) {
        CommandSignInRegistration command = providerRegistration.getCommand(request);
        try {
            user = command.execute();
            return view.menu(user);
        } catch (ControllerException e) {
            return "Some problems with library";
        } catch (NullPointerException n) {
            return view.enterOrRegistration();
        }
    }

    public String executeMenu(int request) {
        if (request == 0) {
            user = null;
            return view.enterOrRegistration();
        }
        if (user.getType().equals("admin")) {
            CommandExecute command = providerAdmin.getCommand(request);
            try {
                String response = command.execute();
                return response + "\n" + view.menu(user);
            } catch (ControllerException | NullPointerException n) {
                return "Some problems in Library, please, try again later" + "\n" + view.menu(user);
            }
        } else {
            CommandExecute command = providerUser.getCommand(request);
            try {
                String response = command.execute();
                return response + "\n" + view.menu(user);
            } catch (ControllerException c) {
                return "Some problems in Library, please, try again later" + "\n" + view.menu(user);
            }
        }
    }
}
