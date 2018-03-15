package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
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
        User user3 = new User(3, 18,"Ольга", "Москва");
        users.add(user3);
        User user4 = new User(4, 45,"Светлана", "Сочи");
        users.add(user4);
        sortUser.sort(users);
        assertThat(new User[] {user3, user1, user2, user4}, is(sortUser.sort(users).toArray()));
    }
}
