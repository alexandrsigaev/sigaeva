package ru.job4j.worktraker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.worktracker.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class StartUITest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void backOutput() {
        System.setOut(stdout);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenFindAllThenFoundAllTickets() {
        Tracker tracker = new Tracker();
        Item test1 = new Item("test1", "testDescription_1", (int) System.currentTimeMillis() / 100);
        tracker.add(test1);
        Item test2 = new Item("test2", "testDescription_2", (int) System.currentTimeMillis() / 100);
        tracker.add(test2);
        Item test3 = new Item("test3", "testDescription_3", (int) System.currentTimeMillis() / 100);
        tracker.add(test3);
        Input input = new StubInput(new String[] {"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(new String (out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(printMenu())
                        .add("------All tickets------")
                        .add("id: " + test1.getId() + " 1name: " + test1.getName() + " description: " + test1.getDesc() + " create " + test1.getCreated())
                        .add("id: " + test2.getId() + " 1name: " + test2.getName() + " description: " + test2.getDesc() + " create " + test2.getCreated())
                        .add("id: " + test3.getId() + " 1name: " + test3.getName() + " description: " + test3.getDesc() + " create " + test3.getCreated())
                        .add("------Complete------")
                        .add(printMenu())
                        .add("")
                        .toString()
                )
        );
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
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
    public void whenFindByIdThenReturnFound() {
        Tracker tracker = new Tracker();
        Item test1 = new Item("test1", "testDescription_1", (int) System.currentTimeMillis() / 100);
        tracker.add(test1);
        Item test2 = new Item("test2", "testDescription_2", (int) System.currentTimeMillis() / 100);
        tracker.add(test2);
        Item test3 = new Item("test3", "testDescription_3", (int) System.currentTimeMillis() / 100);
        tracker.add(test3);
        Input input = new StubInput(new String[] {"4", test2.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(printMenu())
                        .add("------Found tickets------")
                        .add("id: " + test2.getId() + " name: " + test2.getName() + " description: " + test2.getDesc() + " create " + test2.getCreated())
                        .add("------Complete------")
                        .add(printMenu())
                        .add("")
                        .toString()
                )
        );
    }

    @Test
    public void whenFindByNameThenReturnFound() {
        Tracker tracker = new Tracker();
        Item test1 = new Item("test1", "testDescription_1", (int) System.currentTimeMillis() / 100);
        tracker.add(test1);
        Item test2 = new Item("test2", "testDescription_2", (int) System.currentTimeMillis() / 100);
        tracker.add(test2);
        Item test3 = new Item("test3", "testDescription_3", (int) System.currentTimeMillis() / 100);
        tracker.add(test3);
        Input input = new StubInput(new String[] {"5", test2.getName(), "6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(
                new StringJoiner(System.lineSeparator())
                        .add(printMenu())
                        .add("------Found tickets------")
                        .add("id: " + test2.getId() + " 1name: " + test2.getName() + " description: " + test2.getDesc() + " create " + test2.getCreated())
                        .add("------Complete------")
                        .add(printMenu())
                        .add("")
                        .toString()
                )
        );
    }


    private String printMenu() {
        String menu = new StringJoiner(System.lineSeparator())
                .add("------MENU------")
                .add("0. Add new Item")
                .add("1. Show all items")
                .add("2. Edit item")
                .add("3. Delete item")
                .add("4. Find item by Id")
                .add("5. Find items by name")
                .add("6. Exit Program")
                .add("")
                .toString();
        return menu;

    }

}
