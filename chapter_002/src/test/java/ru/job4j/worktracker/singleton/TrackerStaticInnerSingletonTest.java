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
public class TrackerStaticInnerSingletonTest {

    @Test
    public void whenCreateSingletonInstance() {
        Tracker tracker1 = TrackerStaticInnerSingleton.getInstance();
        Tracker tracker2 = TrackerStaticInnerSingleton.getInstance();
        Tracker tracker3 = TrackerStaticInnerSingleton.getInstance();
        Tracker tracker4 = TrackerStaticInnerSingleton.getInstance();

        assertTrue(tracker1 == tracker2 && tracker3 == tracker4 && tracker1 == tracker4);
    }

}