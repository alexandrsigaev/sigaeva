package ru.job4j.worktracker.singleton;

import ru.job4j.worktracker.Tracker;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.01.2019
 */
public class TrackerEagerSingleton {
    private static final Tracker INSTANCE = new Tracker();

    private TrackerEagerSingleton() {
    }

    public static Tracker getInstance() {
        return INSTANCE;
    }
}
