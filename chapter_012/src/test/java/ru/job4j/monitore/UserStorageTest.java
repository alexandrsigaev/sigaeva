package ru.job4j.monitore;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 18.09.2018
 */
public class UserStorageTest {

    private UserStorage storage;

    @Before
    public void setUp() {
        storage = new UserStorage();
    }


    @Test
    public void whenUseThreeThreadWithUserStorage() {
        Thread thread1 = new Thread(() -> storage.add(new User(1, 100)));
        Thread thread2 = new Thread(() -> storage.add(new User(2, 500)));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        storage.transfer(1, 2, 35);
        assertThat(storage.getUsers().get(1).getAmount(), is(65));
        assertThat(storage.getUsers().get(2).getAmount(), is(535));
        assertTrue(storage.update(new User(1, 18956)));
        assertThat(storage.getUsers().get(1).getAmount(), is(18956));
        assertTrue(storage.delete(new User(2, 535)));


    }



}
