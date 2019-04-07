package ru.job4j.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.01.2019
 */
public class CheckAbuses {

    private static final Logger LOGGER = LogManager.getLogger(CheckAbuses.class.getName());

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            String readBufferLine;
            while ((readBufferLine = reader.readLine()) != null) {
                for (String abs : abuse) {
                    readBufferLine = readBufferLine.replaceAll("\\b" + abs + " \\b", "");
                }
                writer.write(readBufferLine + "\n");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
