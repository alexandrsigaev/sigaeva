package ru.job4j.httpexample.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.01.2019
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String role;
    private LocalDateTime createDate;

    public User(String name, String login, String password, String email, String role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createDate = LocalDateTime.now();
    }

    public User(int id, String name, String login, String password, String email, String role, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + ", role='" + role + '\''
                + ", createDate=" + createDate + '}';
    }
}
