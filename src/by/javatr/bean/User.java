package by.javatr.bean;


import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable { //каждое поле должно быть сериалезуемо
    Login login;                           //transient означает, что поле должо быть проигнорировано при сериализации
    Password password;
    String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(type, user.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, type);
    }

    @Override
    public String toString() {
        return "User{" +
                "login=" + login +
                ", password=" + password +
                ", type='" + type + '\'' +
                '}';
    }

    public User() {
    }

    public User(Login login, Password password, String type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Login getLogin() {
        return login;
    }

    public Password getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }
}
