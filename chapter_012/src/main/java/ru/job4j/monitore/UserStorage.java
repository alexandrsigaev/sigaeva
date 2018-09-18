package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 18.09.2018
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private Map<Integer, User> users;

    public UserStorage() {
        this.users = new HashMap<>();
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public boolean add (User user) {
        boolean result = false;
        synchronized (this) {
            if (!this.users.containsKey(user.getId())) {
                this.users.put(user.getId(), user);
                result = true;
            }
        }
        return result;
    }

    public boolean update(User user) {
        boolean result = false;
        synchronized (this) {
            if (this.users.containsKey(user.getId())) {
                this.users.put(user.getId(), user);
                result = true;
            }
        }
        return result;
    }

    public boolean delete(User user) {
        boolean result = false;
        synchronized (this) {
            if (this.users.containsKey(user.getId())) {
                this.users.remove(user.getId());
                result = true;
            }
        }
        return result;
    }

    public void transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            User userFrom = this.users.get(fromId);
            User userTo = this.users.get(toId);
            if (userFrom.getAmount() > amount) {
                userFrom.setAmount(userFrom.getAmount() - amount);
                userTo.setAmount(userTo.getAmount() + amount);
            }
        }
    }

}
