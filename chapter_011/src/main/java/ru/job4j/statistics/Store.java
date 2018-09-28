package ru.job4j.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 26.09.2018
 */
public class Store {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        Map<Integer, User> prevMap = new HashMap<>();
        Map<Integer, User> currMap = new HashMap<>();
        for (User user : previous) {
            prevMap.put(user.id, user);
        }
        for (User user : current) {
            currMap.put(user.id, user);
        }
        this.statisticsAddsAndChangeUsers(prevMap, currMap, result);
        this.statisticsDelUsers(prevMap, currMap, result);
        return result;
    }

    private void statisticsAddsAndChangeUsers(Map<Integer, User> prev, Map<Integer, User> curr, Info info) {
        for (Map.Entry<Integer, User> entry : curr.entrySet()) {
            if (prev.containsKey(entry.getKey()) && !prev.get(entry.getKey()).equals(entry.getValue())) {
                info.info[info.indexCountChangeUsers]++;
            } else if (!prev.containsKey(entry.getKey())) {
                info.info[info.indexCountAddUsers]++;
            }
        }
    }

    private void statisticsDelUsers(Map<Integer, User> prev, Map<Integer, User> curr, Info info) {
        for (Map.Entry<Integer, User> entry : prev.entrySet()) {
            if (!curr.containsKey(entry.getKey())) {
                info.info[info.indexCountDelUsers]++;
            }
        }
    }

    static class Info {
        int[] info = new int[3];
        int indexCountAddUsers = 0;
        int indexCountDelUsers = 1;
        int indexCountChangeUsers = 2;

        @Override
        public String toString() {
            return String.format("Statistics:  Add Users %d, Del Users %d, Change Users %d",
                    this.info[indexCountAddUsers], this.info[indexCountDelUsers], this.info[indexCountChangeUsers]);
        }
    }

    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
}
