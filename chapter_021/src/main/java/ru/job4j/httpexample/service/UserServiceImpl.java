package ru.job4j.httpexample.service;

import ru.job4j.httpexample.dao.Store;
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
public class UserServiceImpl implements UserService {

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private final Store<User> userDAO = UserDAO.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(Map<String, String[]> mapReq) {
        User user = new User(
                mapReq.get("name")[0],
                mapReq.get("login")[0],
                mapReq.get("password")[0],
                mapReq.get("email")[0],
                mapReq.get("role")[0]);
        boolean result = false;
        if (this.userDAO.userLoginIsExists(user.getLogin()) == null && this.validAttributeOfNull(mapReq)) {
            result = userDAO.add(user);
        }
        return result;
    }

    @Override
    public boolean update(Map<String, String[]> mapReq) {
        int id = Integer.parseInt(mapReq.get("id")[0]);
        boolean result = false;
        User tmp = this.findById(id);
        if (tmp != null && this.validAttributeOfNull(mapReq)) {
            User user = new User(id,
                    mapReq.get("name")[0],
                    mapReq.get("login")[0],
                    mapReq.get("password")[0],
                    mapReq.get("email")[0],
                    mapReq.get("role")[0],
                    tmp.getCreateDate());
            result = this.userDAO.update(user);
        }
        return result;
    }

    @Override
    public boolean delete(Map<String, String[]> mapReq) {
        int id = Integer.parseInt(mapReq.get("id")[0]);
        boolean result = false;
        User tempUser = this.findById(id);
        if (tempUser != null) {
            result = this.userDAO.delete(tempUser);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    private boolean validAttributeOfNull(Map<String, String[]> mapReq) {
        boolean res = false;
        for (Map.Entry<String, String[]> entry : mapReq.entrySet()) {
            if (entry.getValue()[0].length() == 0) {
                res = true;
                break;
            }
        }
        return !res;
    }

    @Override
    public boolean isCredentional(String login, String password) {
        boolean result = false;
        User user = userDAO.userLoginIsExists(login);
        if (user != null) {
            result = user.getPassword().equals(password);
        }
        return result;
    }

    @Override
    public User findUserByLogin(String login) {
        return userDAO.userLoginIsExists(login);
    }
}
