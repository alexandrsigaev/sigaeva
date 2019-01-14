package ru.job4j.worktracker.singleton;

import ru.job4j.worktracker.Tracker;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.01.2019
 */
public class TrackerLazySingleton {

    private static Tracker instance;

    private TrackerLazySingleton() {
        this.instance = new Tracker();
    }

    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }
}
