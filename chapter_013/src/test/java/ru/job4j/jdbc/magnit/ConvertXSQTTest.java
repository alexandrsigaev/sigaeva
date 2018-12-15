package ru.job4j.jdbc.magnit;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.12.2018
 */
public class ConvertXSQTTest {

    private Config config = new Config();
    private XmlUsage usage = new XmlUsage();
    private ConvertXSQT convertXSQT = new ConvertXSQT();
    private File sours = new File("fieldsSours.xml");
    private File dest = new File("fieldsDest.xml");

    @Before
    public void setUp() {
        config.init();
        config.generate(5);
        usage.createXml(config.selecrData(), "fieldsSours.xml");
    }

    @Test
    public void whenConvertXmlFile() throws IOException {
        convertXSQT.convert(sours, dest, new File("scheme.xml"));
        assertThat(Files.readAllLines(Paths.get("fieldsDest.xml")), is(Files.readAllLines(Paths.get("convert.xml"))));
    }

}