package ru.job4j.jdbc.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.12.2018
 */
public class XmlUsage {

    private final static Logger LOGGER = LogManager.getLogger(XmlUsage.class);

    public void createXml(List<Field> fields, String file) {
        try {
            JAXBContext jaxbContext  = JAXBContext.newInstance(Entry.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entry(fields), new File(file));

        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public Entry convertXmlToObj(String file) {
        Entry result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Entry.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            result = (Entry) unmarshaller.unmarshal(new File(file));
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @XmlRootElement
    public static class Entry {
        private List<Field> values;

        public Entry() { }

        public Entry(List<Field> values) {
            this.values = values;
        }

        public List<Field> getValues() {
            return values;
        }

        public void setValues(List<Field> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    public static class Field {
        private int value;

        public Field() { }

        public Field(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Field)) {
                return false;
            }
            Field field = (Field) o;
            return getValue() == field.getValue();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue());
        }
    }
}
