package ru.job4j.worktracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class
 * @author sigaevaleksandr
 * @since 19.02.2018
 */
public class Tracker implements ITracker {
    private List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    public Item add(Item item) {
        this.items.add(item);
        item.setId(generateId());
        return item;
    }

    public void replace(String id, Item item) {
        items.set(items.indexOf(this.findById(id)), item);
        item.setId(id);
    }

    public void delete(String id) {
        this.items.remove(this.items.indexOf(findById(id)));
    }

    public List<Item> findAll() {
        return this.items;
    }

    public List<Item> findByName(String key) {
        return items.stream().filter(item -> item != null && item.getName().equals(key)).collect(Collectors.toList());
    }

    public Item findById(String id) {
        Optional<Item> result = items.stream().filter(item -> item != null && item.getId().equals(id)).findAny();
        return result.orElse(null);
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
