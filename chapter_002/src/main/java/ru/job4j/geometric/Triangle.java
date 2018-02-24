package ru.job4j.geometric;

import java.util.StringJoiner;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        StringJoiner triangle = new StringJoiner(System.lineSeparator());
        triangle.add("  +  ");
        triangle.add(" +++ ");
        triangle.add("+++++");
        return triangle.toString();
    }
}
