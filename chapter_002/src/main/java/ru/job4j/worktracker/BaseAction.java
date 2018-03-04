package ru.job4j.worktracker;

/**
 * Class
 * @author sigaevaleksandr
 * @since 03.03.2018
 */
public abstract class BaseAction implements UserAction {
    private final int key;
    private final String name;

    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", key, name);
    }
}
