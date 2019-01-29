package ru.job4j.httpexample.service;

import ru.job4j.httpexample.dao.UserDAO;
import ru.job4j.httpexample.model.User;

import java.util.List;
import java.util.Map;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.01.2019
 */
public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDAO userDAO = UserDAO.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public boolean add(Map<String, String[]> mapReq) {
        User user = new User(
                mapReq.get("name")[0],
                mapReq.get("login")[0],
                mapReq.get("password")[0],
                mapReq.get("email")[0]);
        boolean result = false;
        if (!this.userDAO.userLoginIsExists(user.getLogin())) {
            result = userDAO.add(user);
        }
        return result;
    }

    public boolean update(Map<String, String[]> mapReq) {
        int id = Integer.parseInt(mapReq.get("id")[0]);
        boolean result = false;
        User tmp = this.findById(id);
        if (tmp != null) {
            User user = new User(id,
                    mapReq.get("name")[0],
                    mapReq.get("login")[0],
                    mapReq.get("password")[0],
                    mapReq.get("email")[0],
                    tmp.getCreateDate());
            result = this.userDAO.update(user);
        }
        return result;
    }

    public boolean delete(Map<String, String[]> mapReq) {
        int id = Integer.parseInt(mapReq.get("id")[0]);
        boolean result = false;
        User tempUser = this.findById(id);
        if (tempUser != null) {
            result = this.userDAO.delete(tempUser);
        }
        return result;
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    private User findById(int id) {
        return userDAO.findById(id);
    }
}
