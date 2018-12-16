package ru.job4j.jdbc;

import org.junit.Test;
import ru.job4j.worktracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.12.2018
 */
public class TrackerSQLTest {


    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc", System.currentTimeMillis()));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void replaceItem() throws Exception {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc", System.currentTimeMillis()));
            String id = tracker.findByName("name").get(0).getId();
            tracker.replace(id, new Item("name1", "desc1", System.currentTimeMillis()));
            assertThat(tracker.findByName("name1").size(), is(1));
            assertThat(tracker.findByName("name1").get(0).getId(), is(id));
        }
    }
}