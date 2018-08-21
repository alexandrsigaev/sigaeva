package ru.job4j.map;

import java.util.Calendar;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 21.08.2018
 */
public class User {

    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + this.birthday.hashCode();
        return result;
    }
}
