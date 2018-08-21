package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 21.08.2018
 */
public class UserTest {

    @Test
    public void withOverrideHashcodeAndEquals(){
        User user1 = new User("Oleg", 5, new GregorianCalendar(1980, 5, 12));
        User user2 = new User("Oleg", 5, new GregorianCalendar(1980, 5, 12));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, "user1");
        map.put(user2, "user2");
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }
}
