package ru.job4j.worktracker.singleton;

import org.junit.Test;
import ru.job4j.worktracker.Tracker;

import static org.junit.Assert.*;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.01.2019
 */
public class TrackerEnumSingletonTest {

    @Test
    public void whenGetInstance() {
        Tracker tracker1 = TrackerEnumSingleton.INSTANCE.getInstance();
        Tracker tracker2 = TrackerEnumSingleton.INSTANCE.getInstance();
        assertTrue(tracker1 == tracker2);
    }

}