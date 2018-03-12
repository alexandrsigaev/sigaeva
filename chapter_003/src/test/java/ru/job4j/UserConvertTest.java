package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 12.03.2018
 */
public class UserConvertTest {
    @Test
    public void whenGetListUsersThenReturnMap() {
        UserConvert convert = new UserConvert();
        List<User> users = new ArrayList<>();
        HashMap<Integer, User> result = new HashMap<>();
        User user1 = new User(1, "Вася", "Воронеж");
        users.add(user1);
        result.put(user1.getId(), user1);
        User user2 = new User(2, "Петя", "Санкт-Петербург");
        users.add(user2);
        result.put(user2.getId(), user2);
        User user3 = new User(3, "Ольга", "Москва");
        users.add(user3);
        result.put(user3.getId(), user3);
        User user4 = new User(4, "Светлана", "Сочи");
        users.add(user4);
        result.put(user4.getId(), user4);
        assertThat(convert.process(users), is(result));
    }
}
