package ru.job4j.worktraker;

import org.junit.Test;
import ru.job4j.worktracker.Item;
import ru.job4j.worktracker.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Class
 * @author sigaevaleksandr
 * @since 19.02.2018
 */
public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenFindByNameThenReturnArrayFound() {
        Tracker tracker = new Tracker();
        Item notFound = new Item("test1", "testDescription", 123L);
        tracker.add(notFound);
        Item foundOne = new Item("testFind", "testDescription", 1234L);
        tracker.add(foundOne);
        Item notFound2 = new Item("test2", "testDescription", 12345L);
        tracker.add(notFound2);
        Item foundTwo = new Item("testFind", "testDescription", 1237L);
        tracker.add(foundTwo);
        Item[] found = tracker.findByName("testFind");
        assertThat(new Item[] {foundOne, foundTwo}, is(found));
    }

    @Test
    public void whenDeleteItemThenReturnWithoutThisItem() {
        Tracker tracker = new Tracker();
        Item test1 = new Item("test1", "testDescription", 123L);
        tracker.add(test1);
        Item test2 = new Item("test2", "testDescription", 1234L);
        tracker.add(test2);
        Item test3 = new Item("test3", "testDescription", 12345L);
        tracker.add(test3);
        Item delete = new Item("delete", "testDescription", 1247L);
        tracker.add(delete);
        Item test4 = new Item("test4", "testDescription", 1277L);
        tracker.add(test4);
        Item test5 = new Item("test5", "testDescription", 1237L);
        tracker.add(test5);
        tracker.delete(delete.getId());
        assertThat(new Item[] {test1, test2, test3, test4, test5}, is(tracker.findAll()));
    }
}
