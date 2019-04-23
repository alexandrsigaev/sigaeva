package ru.job4j.httpexample.service;

import ru.job4j.httpexample.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    boolean add(Map<String, String[]> mapReq);

    boolean add(User user);

    boolean update(Map<String, String[]> mapReq);

    boolean delete(Map<String, String[]> mapReq);

    List<User> findAll();

    User findById(int id);

    boolean isCredentional(String login, String password);

    User findUserByLogin(String login);

}
