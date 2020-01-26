package by.javatr.dao;

import by.javatr.bean.Login;
import by.javatr.bean.User;
import by.javatr.dao.exception.DaoException;

import java.util.List;

public interface UserDao {
    void saveUser(User user) throws DaoException;

    User giveUser(Login login) throws DaoException;

    List<User> giveAllUsers();

    void saveListUsers(List<User> users) throws DaoException;
}
