package ru.job4j.jdbc.magnit;

import org.junit.Before;
import org.junit.Test;

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
public class ConfigTest {

    private Config config = new Config();

    @Before
    public void setUp() {
        config.generate(5);
    }

    @Test
    public void whenSelectDataThenGetListFields() {
        List<XmlUsage.Field> fields = Arrays.asList(new XmlUsage.Field(1), new XmlUsage.Field(2), new XmlUsage.Field(3),
                new XmlUsage.Field(4), new XmlUsage.Field(5));
        assertThat(fields, is(config.selectData()));
    }

}