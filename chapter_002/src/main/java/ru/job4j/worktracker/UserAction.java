package ru.job4j.worktracker;

/**
 * Class
 * @author sigaevaleksandr
 * @since 24.02.2018
 */
public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
