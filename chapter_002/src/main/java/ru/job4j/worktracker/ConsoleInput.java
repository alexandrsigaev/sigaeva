package ru.job4j.worktracker;

import java.util.Scanner;

/**
 * Class
 * @author sigaevaleksandr
 * @since 21.02.2018
 */
public class ConsoleInput implements Input {
    @Override
    public String ask(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }
}
