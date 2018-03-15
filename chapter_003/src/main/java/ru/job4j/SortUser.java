package ru.job4j;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 15.03.2018
 */
public class SortUser {
    public Set<User> sort(List<User> users){
        Set<User> result = new TreeSet<>();
        for (User user : users) {
            result.add(user);
        }
        return result;
    }
}
