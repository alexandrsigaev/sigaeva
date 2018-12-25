package ru.job4j;

import java.util.Comparator;
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

    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    /**
     * Метод сортирует List<User> по длине имени
     * @param users Исходный не отсортированный лист юзеров
     * @return Отсортированный List<User>
     */
    public List<User> sortNameLength(List<User> users) {
        users.sort(Comparator.comparingInt(o -> o.getName().length()));
         return users;
    }

    /**
     *Метод сортирует List<User> по обоим полям, сначала сортировка по имени в лексикографическом порядке, потом по возрасту.
     * @param users Исходный не отсортированный List<User>
     * @return Отсортированный List<User>
     */
    public List<User> sortByAllFields(List<User> users) {
        users.sort(Comparator.comparing(User::getName).thenComparing(User::getAge));
        return users;
    }

}
