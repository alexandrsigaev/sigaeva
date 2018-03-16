package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 15.03.2018
 */
public class SortUserTest {

    @Test
    public void whenGetListUsersThanReturnSortedTreeSet() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        User user1 = new User(1, 25, "Вася", "Воронеж");
        users.add(user1);
        User user2 = new User(2, 30, "Петя", "Санкт-Петербург");
        users.add(user2);
        User user3 = new User(3, 18, "Ольга", "Москва");
        users.add(user3);
        User user4 = new User(4, 45, "Светлана", "Сочи");
        users.add(user4);
        sortUser.sort(users);
        assertThat(new User[] {user3, user1, user2, user4}, is(sortUser.sort(users).toArray()));
    }

    @Test
    public void whenGetListUsersThanReturnSortedFromNameLength() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        User evg = new User(4, 45, "Евгений", "Сочи");
        users.add(evg);
        User pet = new User(2, 30, "Петя", "Санкт-Петербург");
        users.add(pet);
        User olg = new User(3, 18, "Ольга", "Москва");
        users.add(olg);
        User vas = new User(1, 25, "Вася", "Воронеж");
        users.add(vas);
        User svet = new User(4, 45, "Светлана", "Сочи");
        users.add(svet);
        assertThat(Arrays.asList(pet, vas, olg, evg, svet), is(sortUser.sortNameLength(users)));
    }

    @Test
    public void whenGetListUsersThanReturnSortedFromOllFields() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        User evg = new User(4, 45, "Евгений", "Сочи");
        users.add(evg);
        User pet = new User(2, 30, "Петя", "Санкт-Петербург");
        users.add(pet);
        User olg = new User(3, 18, "Ольга", "Москва");
        users.add(olg);
        User vas = new User(1, 25, "Вася", "Воронеж");
        users.add(vas);
        User svet = new User(4, 45, "Светлана", "Сочи");
        users.add(svet);
        User ti = new User(6, 4, "Ти", "Сахалин");
        users.add(ti);
        User olg1 = new User(3, 28, "Ольга", "Москва");
        users.add(olg1);
        assertThat(Arrays.asList(ti, vas, pet, olg, olg1, evg, svet), is(sortUser.sortByAllFields(users)));
    }
}
