package ru.job4j.jdbc.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.12.2018
 */
public class Config implements AutoCloseable {

    private final static Logger LOGGER = LogManager.getLogger(Config.class);
    private final Connection connection;

    public Config(Connection connection) {
        this.connection = connection;
        this.createBD();
    }

    private void createBD() {
        String dropTable = "DROP TABLE IF EXISTS entry";
        String createTable = "CREATE TABLE IF NOT EXISTS entry (field INTEGER);";

        try (Statement statement = this.connection.createStatement()) {
            statement.executeUpdate(dropTable);
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void generate(int n) {
        try {
            this.connection.setAutoCommit(false);
            try (PreparedStatement prst = this.connection.prepareStatement("INSERT INTO entry VALUES (?)")) {
                for (int i = 1; i <= n; i++) {
                    prst.setInt(1, i);
                    prst.addBatch();
                }
                prst.executeBatch();
                this.connection.commit();
            }
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
            LOGGER.error(e.getMessage(), e);
        }

    }

    public List<XmlUsage.Field> selectData() {
        List<XmlUsage.Field> result = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()) {
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
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
