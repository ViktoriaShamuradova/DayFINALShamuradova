package by.javatr.service.impl;

import by.javatr.bean.Login;
import by.javatr.bean.Password;
import by.javatr.bean.User;
import by.javatr.dao.exception.DaoException;
import by.javatr.dao.impl.UserDaoImpl;
import by.javatr.service.UserService;
import by.javatr.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
    }

    private static final UserServiceImpl instance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return instance;
    }

    private UserDaoImpl userDao;

    @Override
    public User signIn(String loginString, String passwordString) {
        Login login = new Login(loginString);
        User user = userDao.giveUser(login);
        Password password = new Password(passwordString);
        if (user.getPassword().equals(password)) return user;
        else {
            return null;
        }
    }

    @Override
    public List<User> allAdmins() {
        List<User> users = userDao.giveAllUsers();
        List<User> admins = new ArrayList<>();
        for (User user : users) {
            if (user.getType().equals("admin")) {
                admins.add(user);
            }
        }
        return admins;
    }

    @Override
    public List<User> allClients() {
        List<User> users = userDao.giveAllUsers();
        List<User> clients = new ArrayList<>();
        for (User user : users) {
            if (user.getType().equals("user")) {
                clients.add(user);
            }
        }
        return clients;
    }

    @Override
    public void signOut() throws ServiceException {
    }

    @Override
    public User createAccountClient(Login login, Password password) throws ServiceException {
        User user = new User(login, password, "user");
        return saveUser(user);
    }

    @Override
    public User createAccountAdmin(Login login, Password password) throws ServiceException {
        User user = new User(login, password, "admin");
        return saveUser(user);
    }

    private User saveUser(User user) throws ServiceException {
        try {
            userDao.saveUser(user);
            return user;
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
    }

    @Override
    public boolean deleteUser(Login login) {
        List<User> users = userDao.giveAllUsers();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getLogin().equals(login)) {
                iterator.remove();
                userDao.saveListUsers(users);
                return true;
            }
        }
        return false;
    }

    public boolean isThereSuchLogin(Login login) {
        List<User> allUsers = userDao.giveAllUsers();
        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
