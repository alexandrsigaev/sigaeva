package ru.job4j.statistics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 28.09.2018
 */
public class StoreTest {

    private Store store;
    private List<Store.User> previous;
    private List<Store.User> current;
    private Store.User user1;
    private Store.User user2;
    private Store.User user3;
    private Store.User user4;
    private Store.User user5;
    private Store.User user6;

    @Before
    public void setUp() {
        this.store = new Store();
        this.user1 = new Store.User(12, "Alex");
        this.user2 = new Store.User(82, "Misha");
        this.user3 = new Store.User(48, "Olga");
        this.user4 = new Store.User(32, "Ivan");
        this.user5 = new Store.User(98, "Jon");
        this.user6 = new Store.User(134, "Gery");
        this.previous = new ArrayList<>();
        this.current = new ArrayList<>();
        this.previous.add(user1);
        this.previous.add(user2);
        this.previous.add(user3);
        this.previous.add(user4);
        this.previous.add(user5);
        this.previous.add(user6);
    }

    @Test
    public void whenAdd2UsersChange3UsersDell1Users() {
        this.current.addAll(previous);
        this.current.add(new Store.User(64, "Anton"));
        this.current.add(new Store.User(485, "Anastasia"));
        this.current.set(this.current.indexOf(user2), new Store.User(82, "Inga"));
        this.current.set(this.current.indexOf(user3), new Store.User(48, "Dima"));
        this.current.set(this.current.indexOf(user6), new Store.User(134, "Vasya"));
        this.current.remove(user4);
        Store.Info info = this.store.diff(this.previous, this.current);
        assertThat(info.toString(), is("Statistics:  Add Users 2, Del Users 1, Change Users 3"));
        this.current.clear();
    }

    @Test
    public void whenAdd3UsersOnly() {
        this.current.addAll(previous);
        this.current.add(new Store.User(64, "Anton"));
        this.current.add(new Store.User(485, "Anastasia"));
        this.current.add(new Store.User(654654, "dslkgvkdjggj"));
        Store.Info info = this.store.diff(this.previous, this.current);
        assertThat(info.toString(), is("Statistics:  Add Users 3, Del Users 0, Change Users 0"));
        this.current.clear();
    }

    @Test
    public void whenChange4UsersOnly() {
        this.current.addAll(previous);
        this.current.set(this.current.indexOf(user1), new Store.User(12, "Anton"));
        this.current.set(this.current.indexOf(user2), new Store.User(82, "Anastasia"));
        this.current.set(this.current.indexOf(user3), new Store.User(48, "dslkgvkdjggj"));

        Store.Info info = this.store.diff(this.previous, this.current);
        assertThat(info.toString(), is("Statistics:  Add Users 0, Del Users 0, Change Users 3"));
        this.current.clear();
    }

    @Test
    public void whenDellAllUsers() {
        Store.Info info = this.store.diff(this.previous, this.current);
        assertThat(info.toString(), is("Statistics:  Add Users 0, Del Users 6, Change Users 0"));

    }
}