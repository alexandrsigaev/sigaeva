package ru.job4j.io.sortfile;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 09.01.2019
 */
public class BigFileSorter implements Sorter {

    private int memoryUsage;
    private static final int MEMORY_USAGE_MAX = 1024000;
    private static final int MEMORY_USAGE_DEFAULT = 512000;
    private static final Logger LOGGER = Logger.getLogger(BigFileSorter.class.getName());

    public BigFileSorter() {
        this.memoryUsage = MEMORY_USAGE_DEFAULT;
    }

    public BigFileSorter(int memoryUsage) {
        if (memoryUsage > MEMORY_USAGE_MAX) {
            this.memoryUsage = MEMORY_USAGE_MAX;
        } else {
            this.memoryUsage = memoryUsage;
        }
    }

    @Override
    public void sort(File source, File distance) {
        File file = this.mergeFiles(this.sortStringInTempFiles(this.split(source)));
        try {
            Files.copy(file.toPath(), distance.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    /**
     * Разбвает большой файл на множество маленьких. Размер временных вайлов зависит от
     * параметра memoryUsage.
     *
     * @param file исходный файл, который необходимо разбить
     * @return List<Files> список мелких файлов на которые разбили исходный
     */
    private List<File> split(File file) {
        List<File> result = new ArrayList<>(1000);
        LOGGER.info("Start fraction big file");
        try (var reader = new BufferedReader(new FileReader(file, Charset.defaultCharset()), 200000)) {
            String tmp;
            int count = 0;
            while ((tmp = reader.readLine()) != null) {
                result.add(File.createTempFile("temp" + count++, ".txt"));
                try (var writer = new BufferedWriter(new FileWriter(result.get(count - 1)))) {
                    do {
                        writer.write(tmp);
                        writer.write(System.lineSeparator());
                        tmp = reader.readLine();
                    }
                    while (result.get(count - 1).length() <= this.memoryUsage && tmp != null);
                }
                LOGGER.info("Create temp file" + result.get(count - 1));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Сортирует в памяти все меркие файлы и перезаписывает
     *
     * @param files список исходных мелких файлов
     * @return список мелких файлов в которых произведена сортировка по длинне строки.
     */
    private List<File> sortStringInTempFiles(List<File> files) {
        LOGGER.info("Start sort temp file");
        for (File tempFile : files) {
            List<String> tempListStr;
            try {
                tempListStr = Files.lines(tempFile.toPath())
                        .sorted(Comparator.comparingInt(String::length))
                        .collect(Collectors.toList());
                try (var writer = new BufferedWriter(new FileWriter(tempFile))) {
                    for (String str : tempListStr) {
                        writer.write(str);
                        writer.write(System.lineSeparator());
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
            LOGGER.info(String.format("File %s sorted", tempFile.getName()));
        }
        return files;
    }

    /**
     * Объединяет все временные отсортированные файлы в один отсортированный файл с помощью слияния
     *
     * @param files Лист отсортированных временных файлов
     * @return Выходной файл
     */
    private File mergeFiles(List<File> files) {
        LOGGER.info("Begin to merge sorted files");
        File distance = files.get(0);
        for (int i = 1; i < files.size(); i++) {
            distance = mergeFiles(distance, files.get(i));
            files.get(i).deleteOnExit();
        }
        LOGGER.info("Get sorted file");
        return distance;
    }

    /**
     * Сливает два отсортированных файла с учетом длинны строк
     *
     * @param f1 отсортированный файл
     * @param f2 отсортированный файл
     * @return выходной отсортированный файл полученный с помощью слияния двух входных файлов
     */
    private File mergeFiles(File f1, File f2) {
        LOGGER.info(String.format("Begin to merge files: %s & %s", f1.getName(), f2.getName()));
        File result = null;
        try {
            result = File.createTempFile("merge", "txt");
            try (var readerF1 = new BufferedReader(new FileReader(f1));
                 var readerF2 = new BufferedReader(new FileReader(f2));
                 var writer = new BufferedWriter(new FileWriter(result, true))) {
                String strRead1 = readerF1.readLine();
                String strRead2 = readerF2.readLine();
                while (strRead1 != null || strRead2 != null) {
                    while (strRead2 != null && strRead1 == null) {
                        writer.write(strRead2);
                        writer.write(System.lineSeparator());
                        strRead2 = readerF2.readLine();
                    }
                    while (strRead1 != null && strRead2 == null) {
                        writer.write(strRead1);
                        writer.write(System.lineSeparator());
                        strRead1 = readerF1.readLine();
                    }
                    if (strRead1 == null) {
                        break;
                    }
                    if (strRead1.length() > strRead2.length()) {
                        writer.write(strRead2);
                        strRead2 = readerF2.readLine();
                    } else {
                        writer.write(strRead1);
                        strRead1 = readerF1.readLine();
                    }
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("merge: %s & %s", f1.getName(), f2.getName()));
        return result;
    }
}
