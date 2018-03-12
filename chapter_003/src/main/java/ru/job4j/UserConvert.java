package ru.job4j;

import java.util.HashMap;
import java.util.List;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.03.2018
 */
public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
