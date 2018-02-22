package ru.job4j.worktraker;

import org.junit.Test;
import ru.job4j.worktracker.*;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUserDeleteThenTrackerHasDeleteTicket() {
        Tracker tracker = new Tracker();
        Item test1 = new Item();
        tracker.add(test1);
        Item test2 = new Item();
        tracker.add(test2);
        Item test3 = new Item();
        tracker.add(test3);
        Input input = new StubInput(new String[] {"3", test2.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll(), is(new Item[] {test1, test3}));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }
}
