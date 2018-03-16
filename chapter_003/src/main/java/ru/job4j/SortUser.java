package ru.job4j;

import java.util.*;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 15.03.2018
 */
public class SortUser {
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<>();
        for (User user : users) {
            result.add(user);
        }
        return result;
    }

    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
              @Override
              public int compare(User o1, User o2) {
                  return new SortUser().comparateFromUsersLengthName(o1, o2);
                  }
          });
         return users;
    }

    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = new SortUser().comparateFromUsersLengthName(o1, o2);
                if (result == 0) {
                    result = o1.compareTo(o2);
                }
                return result;
            }
        });
        return users;
    }

    private int comparateFromUsersLengthName(User o1, User o2) {
        int result = 0;
        if (o1.getName().length() > o2.getName().length()) {
            result = 1;
        } else if (o1.getName().length() < o2.getName().length()) {
            result  = -1;
        }
        return result;
    }
}
