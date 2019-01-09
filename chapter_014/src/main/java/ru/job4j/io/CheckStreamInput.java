package ru.job4j.io;

import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.01.2019
 */
public class CheckStreamInput {

    private static final Logger LOGGER = Logger.getLogger(CheckStreamInput.class.getName());

    public boolean isNumber(InputStream in) {
        boolean result = false;
        try (var inputStream = in; var bufferStream = new BufferedInputStream(inputStream)){
            var number = bufferStream.read();
            if (number % 2 == 0) {
                result = true;
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

}
