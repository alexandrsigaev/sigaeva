package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.07.2018
 */
public class UserStoreTest {

    private UserStore userStore;

    @Before
    public void setUp() {
        this.userStore = new UserStore(5);
        userStore.add(new User("123"));
    }

    @Test
    public void whenAddNewUserAndGetHisById() {
        User us1 = new User("25");
        this.userStore.add(us1);
        assertThat(us1, is(this.userStore.findById("25")));
    }

    @Test
    public void whenReplaceUserThenGetNewUser() {
        User us2 = new User("24");
        assertThat(this.userStore.replace("123", us2), is(true));
        assertThat(us2, is(this.userStore.findById("24")));
    }

    @Test
    public void whenDeleteUserThenHisNotFound() {
        assertThat(this.userStore.delete("123"), is(true));
        assertNull(this.userStore.findById("123"));
    }


}