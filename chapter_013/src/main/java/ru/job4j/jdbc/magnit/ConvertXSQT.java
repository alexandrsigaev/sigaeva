package ru.job4j.jdbc.magnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.12.2018
 */
public class ConvertXSQT {

    private final static Logger LOGGER = LogManager.getLogger(ConvertXSQT.class);

    public void convert(File source, File dest, File scheme) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (TransformerConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (TransformerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
