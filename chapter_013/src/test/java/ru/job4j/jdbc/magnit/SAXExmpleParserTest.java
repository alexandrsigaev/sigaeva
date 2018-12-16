package ru.job4j.jdbc.magnit;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.12.2018
 */
public class SAXExmpleParserTest {

    private Config config = new Config();
    private XmlUsage usage = new XmlUsage();
    private ConvertXSQT convertXSQT = new ConvertXSQT();
    private File sours = new File("fieldsSours.xml");
    private File dest = new File("fieldsDest.xml");

    @Before
    public void setUp() {
        config.generate(5);
        usage.createXml(config.selectData(), "fieldsSours.xml");
        convertXSQT.convert(sours, dest, new File("scheme.xml"));
    }

    @Test
    public void whenGetXmlFileThenGetSumItAttributes() {
        assertThat(SAXExmpleParser.calculateSumOfAttributes(dest), is(15));

    }

}