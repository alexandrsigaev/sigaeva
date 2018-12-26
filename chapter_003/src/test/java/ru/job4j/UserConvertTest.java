package ru.job4j;

import org.junit.Test;

import java.util.Map;

import static java.util.Arrays.asList;
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
        User user1 = new User(1, "Вася", "Воронеж");
        User user2 = new User(2, "Петя", "Санкт-Петербург");
        User user3 = new User(3, "Ольга", "Москва");
        User user4 = new User(4, "Светлана", "Сочи");
        assertThat(convert.process(asList(user1, user2, user3, user4)), is(Map.of(
                user1.getId(), user1,
                user2.getId(), user2,
                user3.getId(), user3,
                user4.getId(), user4)
        ));
    }
}
