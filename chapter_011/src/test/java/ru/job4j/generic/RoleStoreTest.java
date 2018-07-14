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
public class RoleStoreTest {

    private RoleStore userStore;

    @Before
    public void setUp() {
        this.userStore = new RoleStore(5);
        userStore.add(new Role("123"));
    }

    @Test
    public void whenAddNewRoleAndGetHisById() {
        Role role = new Role("25");
        this.userStore.add(role);
        assertThat(role, is(this.userStore.findById("25")));
    }

    @Test
    public void whenReplaceRoleThenGetNewRole() {
        Role role = new Role("24");
        assertThat(this.userStore.replace("123", role), is(true));
        assertThat(role, is(this.userStore.findById("24")));
    }

    @Test
    public void whenDeleteRoleThenHisNotFound() {
        assertThat(this.userStore.delete("123"), is(true));
        assertNull(this.userStore.findById("123"));
    }


}