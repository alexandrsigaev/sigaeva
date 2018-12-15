package ru.job4j.jdbc.magnit;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.12.2018
 */
public class SAXExmpleParser {

    private final static Logger LOGGER = Logger.getLogger(SAXExmpleParser.class);

    public static int calculateSumOfAttributes(File file) {
        CountHandler handler = new CountHandler();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, handler);
        } catch (ParserConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (SAXException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return handler.getSum();
    }



    private static class CountHandler extends DefaultHandler {

        private int sum = 0;

        public int getSum() {
            return sum;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("entry")) {
                sum += Integer.parseInt(attributes.getValue("field"));
            }
        }
    }

}
