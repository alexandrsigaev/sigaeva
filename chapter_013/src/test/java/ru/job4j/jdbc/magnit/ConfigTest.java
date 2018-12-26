package ru.job4j.jdbc.magnit;

import org.junit.Test;
import ru.job4j.jdbc.ConnectionRollback;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.12.2018
 */
public class ConfigTest {

    public Connection init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app_magnit.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenSelectDataThenGetListFields() {
        try (Config config = new Config(ConnectionRollback.create(this.init()))) {
            config.generate(5);
            List<XmlUsage.Field> fields = List.of(new XmlUsage.Field(1),
                    new XmlUsage.Field(2), new XmlUsage.Field(3),
                    new XmlUsage.Field(4), new XmlUsage.Field(5));
            assertThat(fields, is(config.selectData()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}