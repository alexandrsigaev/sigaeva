package ru.job4j.worktracker.singleton;

import ru.job4j.worktracker.Tracker;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.01.2019
 */
public class TrackerStaticInnerSingleton {
    private TrackerStaticInnerSingleton() {
    }

    public static Tracker getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Tracker INSTANCE = new Tracker();
    }


}
