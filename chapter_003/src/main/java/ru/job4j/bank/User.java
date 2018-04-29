package ru.job4j.bank;

import java.util.Objects;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 29.04.2018
 */
public class User implements Comparable {
    private String name;
    private String passport;

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getPassport(), user.getPassport());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getPassport());
    }

    @Override
    public int compareTo(Object o) {
        return this.passport.compareTo(((User) o).passport);
    }
}
