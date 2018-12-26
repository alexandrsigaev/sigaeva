package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        User user1 = new User(1, 25, "Вася", "Воронеж");
        User user2 = new User(2, 30, "Петя", "Санкт-Петербург");
        User user3 = new User(3, 18, "Ольга", "Москва");
        User user4 = new User(4, 45, "Светлана", "Сочи");
        assertThat(Set.of(user3, user1, user2, user4), is(sortUser.sort(List.of(user1, user2, user3, user4))));
    }

    @Test
    public void whenGetListUsersThanReturnSortedFromNameLength() {
        SortUser sortUser = new SortUser();
        User evg = new User(4, 45, "Евгений", "Сочи");
        User vas = new User(1, 25, "Вася", "Воронеж");
        User olg = new User(3, 18, "Ольга", "Москва");
        User pet = new User(2, 30, "Петя", "Санкт-Петербург");
        User svet = new User(4, 45, "Светлана", "Сочи");
        List<User> users = new ArrayList<>(List.of(evg, vas, olg, pet, svet));
        assertThat(List.of(vas, pet, olg, evg, svet), is(sortUser.sortNameLength(users)));
    }

    @Test
    public void whenGetListUsersThanReturnSortedFromOllFields() {
        SortUser sortUser = new SortUser();
        User evg = new User(4, 45, "Евгений", "Сочи");
        User olg = new User(3, 18, "Ольга", "Москва");
        User pet = new User(2, 30, "Петя", "Санкт-Петербург");
        User vas = new User(1, 25, "Вася", "Воронеж");
        User svet = new User(4, 45, "Светлана", "Сочи");
        User ti = new User(6, 4, "Ти", "Сахалин");
        User olga = new User(3, 28, "Ольга", "Москва");
        List<User> users = new ArrayList<>(List.of(evg, olg, pet, vas, svet, ti, olga));
        assertThat(List.of(vas, evg, olg, olga, pet, svet, ti), is(sortUser.sortByAllFields(users)));
    }
}
