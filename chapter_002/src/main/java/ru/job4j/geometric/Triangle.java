package ru.job4j.geometric;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 22.02.2018
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder triangle = new StringBuilder();
        triangle.append("  +  ");
        triangle.append(System.lineSeparator());
        triangle.append(" +++ ");
        triangle.append(System.lineSeparator());
        triangle.append("+++++");
        triangle.append(System.lineSeparator());
        return triangle.toString();
    }
}
