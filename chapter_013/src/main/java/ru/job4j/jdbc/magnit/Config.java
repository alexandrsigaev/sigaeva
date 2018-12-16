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

    private final static Logger LOGGER = Logger.getLogger(Config.class);

    public static Connection getConnection() {
        Properties properties = new Properties();
        Connection connection;
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app_magnit.properties")) {
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(properties.getProperty("url"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return connection;
    }

    public Config() {
        this.createBD();
    }

    private void createBD() {
        String dropTable = "DROP TABLE IF EXISTS entry";
        String createTable = "CREATE TABLE IF NOT EXISTS entry (field INTEGER);";

        try (Statement statement = this.getConnection().createStatement()) {
            statement.executeUpdate(dropTable);
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void generate(int n) {
        try {
            this.getConnection().setAutoCommit(false);
            try (PreparedStatement prst = this.getConnection().prepareStatement("INSERT INTO entry VALUES (?)")) {
                for (int i = 1; i <= n; i++) {
                    prst.setInt(1, i);
                    prst.addBatch();
                }
                prst.executeBatch();
                this.getConnection().commit();
            }
        } catch (SQLException e) {
            try {
                this.getConnection().rollback();
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
            LOGGER.error(e.getMessage(), e);
        }

    }

    public List<XmlUsage.Field> selectData() {
        List<XmlUsage.Field> result = new ArrayList<>();
        try (Statement statement = this.getConnection().createStatement()) {
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

        if (this.getConnection() != null) {
            this.getConnection().close();
        }

    }
}
