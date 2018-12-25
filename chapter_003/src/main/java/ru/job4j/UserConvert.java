package ru.job4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.03.2018
 */
public class UserConvert {
    public Map<Integer, User> process(List<User> list) {
        return list.stream().collect(Collectors.toMap(User::getId, user -> user, (oldValue, newValue) -> newValue));
    }
}
