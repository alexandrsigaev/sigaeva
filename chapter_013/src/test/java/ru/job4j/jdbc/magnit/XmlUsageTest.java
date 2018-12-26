package ru.job4j.jdbc.magnit;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.jdbc.ConnectionRollback;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class XmlUsageTest {

    private XmlUsage usage = new XmlUsage();
    private List<XmlUsage.Field> fields;

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

    @Before
    public void setUp() {
        fields = List.of(new XmlUsage.Field(1), new XmlUsage.Field(2), new XmlUsage.Field(3),
                new XmlUsage.Field(4), new XmlUsage.Field(5));
    }

    @Test
    public void whenUseXmlParser() {
        try (Config config = new Config(ConnectionRollback.create(this.init()))) {
            config.generate(5);
            usage.createXml(config.selectData(), "fieldsTest.xml");
            assertThat(Files.readAllLines(Paths.get("fieldsTest.xml")), is(Files.readAllLines(Paths.get("fields.xml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenParseXmlFileToListFields() {
        List<XmlUsage.Field> parseListFields = usage.convertXmlToObj("fieldsTest.xml").getValues();
        assertThat(parseListFields, is(fields));

    }

}