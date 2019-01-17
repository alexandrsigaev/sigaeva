package ru.job4j.archiver;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 17.01.2019
 */
public class ZipArchiver {

    private static final Logger LOGGER = Logger.getLogger(ZipArchiver.class.getName());
    private String path;
    private String zipName;
    private List<String> extension;

    public ZipArchiver(String path, String zipName, String extension) {
        this.path = path;
        this.zipName = zipName;
        this.extension = getListExtension(extension);
    }

    public static void main(String... args) {
        String path = null;
        String zipName = null;
        String extensions = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    path = args[i + 1];
                    break;
                case "-e":
                    StringBuilder tempBuilder = new StringBuilder();
                    while (!args[i + 1].equals("-o")) {
                        tempBuilder.append(String.format("%s,", args[i + 1]));
                        i++;
                    }
                    extensions = tempBuilder.toString();
                    break;
                case "-o":
                    zipName = args[i + 1];
                    break;
            }
        }
        ZipArchiver zipArchiver = new ZipArchiver(path, zipName, extensions);
        LOGGER.info(String.format("Param: %s | %s | %s", zipArchiver.path, zipArchiver.zipName, zipArchiver.extension));
        zipArchiver.zipDirrectory();
    }

    public void zipDirrectory() {
        LOGGER.info("Archiving start");
        File file = new File(this.correctInputPath() + this.zipName);
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file, false))) {
            Path inputPath = Paths.get(path);
            Files.walk(inputPath).filter(p -> !Files.isDirectory(p))
                    .filter(this::filterExtension)
                    .forEach(p -> {
                        LOGGER.info(String.format("zip file: %s", p.toString()));
                        ZipEntry zipEntry = new ZipEntry(inputPath.relativize(p).toString());
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            Files.copy(p, zipOutputStream);
                            zipOutputStream.closeEntry();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage(), e);
                        }
                    });
            LOGGER.info("Archiving completed");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private boolean filterExtension(Path path) {
        boolean res = false;
        for (String ex : this.extension) {
            if (path.toString().endsWith(ex)){
                res = true;
                break;
            }
        }
        return res;
    }

    private String correctInputPath() {
        String[] temp = this.path.split("\\\\");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < temp.length - 1; i++) {
            result.append(temp[i]);
        }
        return result.toString();
    }


    private List<String> getListExtension(String extensions) {
        return Arrays.stream(extensions.split(","))
                .map(s -> String.format(".%s", s)
                        .replaceAll(" ", "")
                        .strip())
                .collect(Collectors.toList());
    }
}
