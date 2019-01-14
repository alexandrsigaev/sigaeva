package ru.job4j.worktracker.singleton;

import ru.job4j.worktracker.Tracker;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.01.2019
 */
public enum TrackerEnumSingleton {
    INSTANCE();
    private Tracker tracker;

    private TrackerEnumSingleton() {
        if (this.tracker == null) {
            this.tracker = new Tracker();
        }
    }

    public Tracker getInstance() {
        return this.tracker;
    }
}
