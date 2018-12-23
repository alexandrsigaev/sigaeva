package ru.job4j.worktraker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.worktracker.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class StartUITest {


    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);
        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(out));
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenFindAllThenFoundAllTickets() {
        Tracker tracker = new Tracker();
        Item test1 = new Item("test1", "testDescription_1", System.currentTimeMillis());
        tracker.add(test1);
        Item test2 = new Item("test2", "testDescription_2", System.currentTimeMillis());
        tracker.add(test2);
        Item test3 = new Item("test3", "testDescription_3", System.currentTimeMillis());
        tracker.add(test3);
        Input input = new StubInput(new String[] {"1", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(this.out.toString(), is(
                new StringJoiner(System.lineSeparator())
                        .add(printMenu())
                        .add("------All tickets------")
                        .add(test1.toString())
                        .add(test2.toString())
                        .add(test3.toString())
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
        new StartUI(input, tracker, output).init();
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
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll(), is(Arrays.asList(test1, test3)));
    }

    @Test
    public void whenFindByIdThenReturnFound() {
        Tracker tracker = new Tracker();
        Item test1 = new Item("test1", "testDescription_1", System.currentTimeMillis());
        tracker.add(test1);
        Item test2 = new Item("test2", "testDescription_2", System.currentTimeMillis());
        tracker.add(test2);
        Item test3 = new Item("test3", "testDescription_3", System.currentTimeMillis());
        tracker.add(test3);
        Input input = new StubInput(new String[] {"4", test2.getId(), "6"});
        new StartUI(input, tracker, output).init();
        assertThat(this.out.toString(), is(
                new StringJoiner(System.lineSeparator())
                        .add(printMenu())
                        .add("------Found tickets------")
                        .add(test2.toString())
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
        Item test1 = new Item("test1", "testDescription_1", System.currentTimeMillis());
        tracker.add(test1);
        Item test2 = new Item("test2", "testDescription_2", System.currentTimeMillis());
        tracker.add(test2);
        Item test3 = new Item("test3", "testDescription_3", System.currentTimeMillis());
        tracker.add(test3);
        Input input = new StubInput(new String[] {"5", test2.getName(), "6"});
        new StartUI(input, tracker, output).init();
        assertThat(this.out.toString(), is(
                new StringJoiner(System.lineSeparator())
                        .add(printMenu())
                        .add("------Found tickets------")
                        .add(test2.toString())
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
                .toString();
        return menu;

    }

}
