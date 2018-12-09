package ru.job4j.worktracker;

import java.util.List;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.12.2018
 */
public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    void delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
