package by.javatr.service;

import by.javatr.bean.Login;
import by.javatr.bean.Password;
import by.javatr.bean.User;
import by.javatr.service.exception.ServiceException;

import java.util.List;


public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    List<User> allAdmins() throws ServiceException;

    List<User> allClients() throws ServiceException;

    void signOut() throws ServiceException;

    User createAccountClient(Login login, Password password) throws ServiceException;

    User createAccountAdmin(Login login, Password password) throws ServiceException;

    boolean deleteUser(Login login) throws ServiceException;
}
