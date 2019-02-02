package ru.job4j.httpexample.dao;

import ru.job4j.httpexample.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 02.02.2019
 */
public class FakeUserDAO implements Store<User> {

    private final List<User> users = new CopyOnWriteArrayList<>();
    private static final FakeUserDAO INSTANCE = new FakeUserDAO();

    private FakeUserDAO() {
        users.add(new User(1, "Vasya", "vasyalogn", "vasya@123.ru", "qwerty123", LocalDateTime.now()));
        users.add(new User(2, "Olya", "olyalogn", "olya@123.ru", "qwerty123", LocalDateTime.now()));
    }

    public static FakeUserDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        user.setId(this.users.size() + 1);
        user.setCreateDate(LocalDateTime.now());
        return users.add(user);
    }

    @Override
    public boolean update(User user) {
        boolean res = false;
        User tmp = this.findById(user.getId());
        if (tmp != null) {
            tmp.setName(user.getName());
            tmp.setLogin(user.getLogin());
            tmp.setPassword(user.getPassword());
            tmp.setEmail(user.getEmail());
            res = true;
        }
        return res;
    }

    @Override
    public boolean delete(User user) {
        return users.remove(user);
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public User findById(int id) {
        List<User> tmp = this.users.stream().filter(user -> user.getId() == id).collect(Collectors.toList());
        return tmp.isEmpty() ? null : tmp.get(0);
    }

    @Override
    public boolean userLoginIsExists(User user) {
        return this.users.stream().anyMatch(u -> u.getLogin().equals(user.getLogin()));
    }
}
