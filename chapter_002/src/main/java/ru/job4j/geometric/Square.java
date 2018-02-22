package ru.job4j.geometric;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder square = new StringBuilder();
        square.append("++++");
        square.append(System.lineSeparator());
        square.append("+  +");
        square.append(System.lineSeparator());
        square.append("+  +");
        square.append(System.lineSeparator());
        square.append("++++");
        square.append(System.lineSeparator());
        return square.toString();
    }
}
