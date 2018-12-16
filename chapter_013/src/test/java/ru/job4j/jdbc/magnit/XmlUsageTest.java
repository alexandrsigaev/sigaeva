package ru.job4j.jdbc.magnit;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.12.2018
 */
public class XmlUsageTest {

    private Config config = new Config();
    private XmlUsage usage = new XmlUsage();
    private List<XmlUsage.Field> fields;

    @Before
    public void setUp() {
        config.generate(5);
        fields = Arrays.asList(new XmlUsage.Field(1), new XmlUsage.Field(2), new XmlUsage.Field(3),
                new XmlUsage.Field(4), new XmlUsage.Field(5));
    }

    @Test
    public void whenUseXmlParser() throws IOException {
        usage.createXml(config.selectData(), "fieldsTest.xml");
        assertThat(Files.readAllLines(Paths.get("fieldsTest.xml")), is(Files.readAllLines(Paths.get("fields.xml"))));
    }

    @Test
    public void whenParseXmlFileToListFields() {
        List<XmlUsage.Field> parseListFields = usage.convertXmlToObj("fieldsTest.xml").getValues();
        assertThat(parseListFields, is(fields));

    }

}