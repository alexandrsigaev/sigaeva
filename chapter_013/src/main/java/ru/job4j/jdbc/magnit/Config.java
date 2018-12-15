package ru.job4j.jdbc.magnit;

import org.apache.log4j.Logger;

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
public class Config implements AutoCloseable {
    private final Properties values = new Properties();
    private Connection connection = null;
    private final static Logger LOGGER = Logger.getLogger(Config.class);

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app_magnit.properties")) {
            values.load(in);
            Class.forName(this.values.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(this.get("url"));
            this.createBD();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }

    private void createBD() {
        String dropTable = "DROP TABLE IF EXISTS entry";
        String createTable = "CREATE TABLE IF NOT EXISTS entry (field INTEGER);";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropTable);
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void generate(int n) {
        try (PreparedStatement prst = connection.prepareStatement("INSERT INTO entry VALUES (?)")) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= n; i++) {
                prst.setInt(1, i);
                prst.addBatch();
            }
            prst.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    public List<XmlUsage.Field> selecrData() {
        List<XmlUsage.Field> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM entry");
            while (resultSet.next()) {
                result.add(new XmlUsage.Field(resultSet.getInt("field")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {

        if (connection != null) {
            connection.close();
        }

    }
}
