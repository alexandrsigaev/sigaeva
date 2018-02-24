package ru.job4j.geometric;

import java.util.StringJoiner;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringJoiner square = new StringJoiner(System.lineSeparator());
        square.add("++++");
        square.add("+  +");
        square.add("+  +");
        square.add("++++");
        return square.toString();
    }
}
