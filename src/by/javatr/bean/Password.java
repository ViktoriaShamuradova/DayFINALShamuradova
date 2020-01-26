package by.javatr.bean;

import java.io.Serializable;
import java.util.Objects;

public class Password implements Serializable {
    private final String password;

    public Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return " password= " + password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        if (null == password) {
            return (password == password1.getPassword());
        } else {
            if (!password.equals(password1.getPassword())) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
