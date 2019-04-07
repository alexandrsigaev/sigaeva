package ru.job4j.chat;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 17.01.2019
 */
public class Chat {

    private static final Logger LOGGER = LogManager.getLogger(Chat.class.getName());
    private static final String PAUSE = "стоп";
    private static final String START = "продолжить";
    private static final String STOP = "закончить";
    private Scanner scanner = new Scanner(System.in);
    private String readLine;
    private Random random = new Random();

    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.createChat();
    }

    public void createChat() {
        StringBuilder info = new StringBuilder()
                .append(System.lineSeparator())
                .append("----------------------------------------------------------------------")
                .append(System.lineSeparator())
                .append("Привет! Я Бот-Иван")
                .append(System.lineSeparator())
                .append("Если вы хотите услышать какие нибудь умные мысли, напишите мне что-нибудь")
                .append(System.lineSeparator())
                .append("Если вам надоел этот поток безсвязной ерунды, напишите 'стоп' и можете продолжать общение сами с собой...")
                .append(System.lineSeparator())
                .append("Для возврата к общению с самым умным ботом введите 'продолжить'.")
                .append(System.lineSeparator())
                .append("Для полного завершения общения напишите 'закончить'.")
                .append(System.lineSeparator())
                .append("-----------------------------------------------------------------------");
        LOGGER.info(info);
        boolean lissen = true;
        while (lissen) {
            this.readLine = scanner.nextLine();
            if (this.readLine.equalsIgnoreCase(STOP)) {
                LOGGER.info("Клиент: " + readLine);
                LOGGER.info("Давай досвидания!");
                lissen = false;
            } else if (this.readLine.equalsIgnoreCase(PAUSE)) {
                LOGGER.info("Клиент: " + readLine);
                LOGGER.info("Окей я ушел в себя, ты знаешь что делать для того чтобы я вернулся!");
                sleep();
            } else {
                LOGGER.info("Клиент: " + readLine);
                LOGGER.info(randomAnswer());
            }
        }
    }

    private String randomAnswer() {
        String result = null;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(
                new File("chapter_014\\source").getAbsolutePath(), "r")) {
            randomAccessFile.seek(this.random.nextInt((int) randomAccessFile.length() - 1));
            result = new String(randomAccessFile.readLine().getBytes("ISO_8859_1"), "UTF8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void sleep() {
        boolean isStart = false;
        while (!isStart) {
            this.readLine = scanner.nextLine();
            if (readLine.equalsIgnoreCase(START)) {
                isStart = true;
            }
            LOGGER.info("Клиент: " + readLine);
        }
    }

}
