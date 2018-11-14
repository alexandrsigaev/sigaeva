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
            if (!prev.containsKey(entry.getKey())) {
                info.info[Info.INDEX_COUNT_ADD_USERS]++;
            } else if (!prev.get(entry.getKey()).equals(entry.getValue())) {
                info.info[Info.INDEX_COUNT_CHANGE_USERS]++;
            }
        }
    }

    private void statisticsDelUsers(Map<Integer, User> prev, Map<Integer, User> curr, Info info) {
        for (Map.Entry<Integer, User> entry : prev.entrySet()) {
            if (!curr.containsKey(entry.getKey())) {
                info.info[Info.INDEX_COUNT_DEL_USERS]++;
            }
        }
    }

    static class Info {
        private int[] info = new int[3];
        private static final int INDEX_COUNT_ADD_USERS = 0;
        private static final int INDEX_COUNT_DEL_USERS = 1;
        private static final int INDEX_COUNT_CHANGE_USERS = 2;

        @Override
        public String toString() {
            return String.format("Statistics:  Add Users %d, Del Users %d, Change Users %d",
                    this.info[INDEX_COUNT_ADD_USERS], this.info[INDEX_COUNT_DEL_USERS], this.info[INDEX_COUNT_CHANGE_USERS]);
        }
    }

    static class User {
        private int id;
        private String name;

        User(int id, String name) {
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
