package by.javatr.dao.impl;

import by.javatr.bean.Login;
import by.javatr.bean.User;
import by.javatr.dao.Reader;
import by.javatr.dao.UserDao;
import by.javatr.dao.Writer;
import by.javatr.dao.exception.DaoException;
import by.javatr.dao.exception.WriterException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {

    private UserDaoImpl() {
        writer = Writer.getInstance();
        reader = Reader.getInstance();
        fileOfUsers = new File("User.bin");
        listOfUsers = reader.readUsersFromFile(fileOfUsers);
    }

    private static final UserDaoImpl instance = new UserDaoImpl();

    public static UserDaoImpl getInstance() {
        return instance;
    }

    private List<User> listOfUsers;
    private Writer writer;
    private Reader reader;
    private File fileOfUsers;

    @Override
    public void saveUser(User user) throws DaoException {
        listOfUsers.add(user);
        try {
            writer.writeUsersToFile(listOfUsers, fileOfUsers);
        } catch (WriterException w) {
            throw new DaoException(w);
        }
    }

    @Override
    public void saveListUsers(List<User> users) throws DaoException {
        listOfUsers = users;
        try {
            writer.writeUsersToFile(listOfUsers, fileOfUsers);
        } catch (WriterException w) {
            throw new DaoException(w);
        }
    }

    @Override
    public User giveUser(Login login) {
        for (User user : listOfUsers) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> giveAllUsers() {
        return new ArrayList<>(listOfUsers);
    }
}

