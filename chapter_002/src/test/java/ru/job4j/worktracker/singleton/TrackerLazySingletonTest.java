package ru.job4j.worktracker.singleton;

import org.junit.Test;
import ru.job4j.worktracker.Tracker;

import static org.junit.Assert.*;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 15.01.2019
 */
public class TrackerLazySingletonTest {

    @Test
    public void whenCreateSingletonInstance() {
        Tracker tracker1 = TrackerLazySingleton.getInstance();
        Tracker tracker2 = TrackerLazySingleton.getInstance();

        assertTrue(tracker1 == tracker2);
    }

}