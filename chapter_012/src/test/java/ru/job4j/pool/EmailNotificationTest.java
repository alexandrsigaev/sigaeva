package ru.job4j.pool;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 23.11.2018
 */
public class EmailNotificationTest {

    private List<User> users;
    private EmailNotification emailNotification;

    @Before
    public void setUp() {
        emailNotification = new EmailNotification();
        users = new ArrayList<>();
        users.add(new User("anna", "anna@list.com"));
        users.add(new User("igor", "igor@list.com"));
        users.add(new User("petua", "petua@list.com"));
        users.add(new User("janna", "janna@list.com"));
        users.add(new User("galina", "galina@list.com"));
        users.add(new User("victor", "victor@list.com"));
        users.add(new User("jorik", "superman@list.com"));

    }

    @Test
    public void whenSendEmail() {
        for (User user : users) {
            emailNotification.emailTo(user);
        }
        emailNotification.close();
    }

}