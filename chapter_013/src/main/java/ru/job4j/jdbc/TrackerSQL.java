package ru.job4j.jdbc;

import ru.job4j.worktracker.ITracker;
import ru.job4j.worktracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.12.2018
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection = null;
    private Properties config = new Properties();
    private Properties trackerScript = new Properties();
    private final List<Item> items = new ArrayList<>();


    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            this.config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            this.initSql();
            this.createDB();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    private boolean initSql() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("tracker.sql")) {
            this.trackerScript.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return trackerScript.isEmpty();
    }

    private void createDB() {
        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate(this.trackerScript.getProperty("CREATE_TABLE_ITEM"));
            statement.executeUpdate(this.trackerScript.getProperty("CREATE_TABLE_COMMENTS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.trackerScript.getProperty("ADD_ITEM"))) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            statement.setTimestamp(3, new Timestamp(item.getCreated()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        try (PreparedStatement statement = this.connection.prepareStatement(this.trackerScript.getProperty("REPLACE_ITEM"))) {
            statement.setString(1, item.getName());
            statement.setString(2, item.getDesc());
            statement.setTimestamp(3, new Timestamp(item.getCreated()));
            statement.setInt(4, Integer.parseInt(id));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement statementComment = this.connection.prepareStatement(this.trackerScript.getProperty("DELETE_ALL_COMMENTS_FOR_ITEM"))) {
            statementComment.setInt(1, Integer.parseInt(id));
            try (PreparedStatement statementItem = this.connection.prepareStatement(this.trackerScript.getProperty("DELETE_ITEM"))) {
                statementItem.setInt(1, Integer.parseInt(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Item> findAll() {
        try (Statement statement = this.connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(this.trackerScript.getProperty("FIND_ALL_ITEMS"))) {
                this.fillListItems(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(this.trackerScript.getProperty("FIND_ITEM_BY_NAME"))) {
            preparedStatement.setString(1, "%" + key + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                fillListItems(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(this.trackerScript.getProperty("FIND_ITEM_BY_ID"))) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                fillListItems(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!items.isEmpty()) {
            result = items.get(0);
        }
        return result;
    }

    private void fillListItems(ResultSet resultSet) throws SQLException {
        items.clear();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Long created = resultSet.getTimestamp("created").getTime();
            Item item = new Item(id.toString(), name, description, created);
            items.add(item);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
