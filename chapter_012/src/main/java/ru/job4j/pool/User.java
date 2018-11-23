package ru.job4j.pool;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 23.11.2018
 */
public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
