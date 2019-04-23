package ru.job4j.httpexample.service;

import ru.job4j.httpexample.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceStubImpl implements UserService {

    private final Map<Integer, User> store = new HashMap<>();
    private Integer ids = 1;

    @Override
    public boolean add(Map<String, String[]> mapReq) {

        User user = new User(
                mapReq.get("name")[0],
                mapReq.get("login")[0],
                mapReq.get("password")[0],
                mapReq.get("email")[0],
                mapReq.get("role")[0]);
        boolean result = false;

        if (!store.containsValue(user)) {
            user.setId(this.ids++);
            store.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
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
                    mapReq.get("role")[0],
                    tmp.getCreateDate());
            result = this.store.replace(tmp.getId(), tmp, user);
        }
        return result;
    }

    @Override
    public boolean delete(Map<String, String[]> mapReq) {
        int id = Integer.parseInt(mapReq.get("id")[0]);
        boolean result = false;
        User tempUser = this.findById(id);
        if (tempUser != null) {
            result = this.store.remove(tempUser.getId(), tempUser);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public User findById(int id) {
        return store.get(id);
    }

    @Override
    public boolean isCredentional(String login, String password) {
        boolean result = false;
        for (Map.Entry<Integer, User> entry : this.store.entrySet()) {
            if (entry.getValue().getLogin().equals(login) && entry.getValue().getPassword().equals(password)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public User findUserByLogin(String login) {
        User result = null;
        for (Map.Entry<Integer, User> entry : this.store.entrySet()) {
            if (entry.getValue().getLogin().equals(login)) {
                result = entry.getValue();
            }
        }
        return result;
    }
}
