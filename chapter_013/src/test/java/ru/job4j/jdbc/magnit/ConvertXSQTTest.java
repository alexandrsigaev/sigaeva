package ru.job4j.jdbc.magnit;

import org.junit.Test;
import ru.job4j.jdbc.ConnectionRollback;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.12.2018
 */
public class ConvertXSQTTest {

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

    private XmlUsage usage = new XmlUsage();
    private ConvertXSQT convertXSQT = new ConvertXSQT();
    private File sours = new File("fieldsSours.xml");
    private File dest = new File("fieldsDest.xml");


    @Test
    public void whenConvertXmlFile() {
        try (Config config = new Config(ConnectionRollback.create(this.init()))) {
            config.generate(5);
            usage.createXml(config.selectData(), "fieldsSours.xml");
            convertXSQT.convert(sours, dest, new File("scheme.xml"));
            assertThat(Files.readAllLines(Paths.get("fieldsDest.xml")),
                    is(Files.readAllLines(Paths.get("convert.xml"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}