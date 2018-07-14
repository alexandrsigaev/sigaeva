package ru.job4j.generic;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 12.07.2018
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
