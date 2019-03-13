package ru.job4j.httpexample.dao;

import java.util.List;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.01.2019
 */
public interface Store<E> {
    boolean add(E e);
    boolean update(E e);
    boolean delete(E e);
    List<E> findAll();
    E findById(int id);
    E userLoginIsExists(String login);
}
