package ru.job4j.worktracker.singleton;

import org.junit.Test;
import ru.job4j.worktracker.Tracker;

import static org.junit.Assert.assertTrue;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 15.01.2019
 */
public class TrackerEagerSingletonTest {

    @Test
    public void whenCreateSingletonInstance() {
        Tracker tracker1 = TrackerEagerSingleton.getInstance();
        Tracker tracker2 = TrackerEagerSingleton.getInstance();
        Tracker tracker3 = TrackerEagerSingleton.getInstance();
        Tracker tracker4 = TrackerEagerSingleton.getInstance();

        assertTrue(tracker1 == tracker2 && tracker3 == tracker4 && tracker1 == tracker4);
    }
}