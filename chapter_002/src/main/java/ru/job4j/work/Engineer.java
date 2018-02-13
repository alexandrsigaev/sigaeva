package ru.job4j.work;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.02.2018
 */
public class Engineer extends Profession {
    private int category;
    private String field;

    public Project makeProject(String technicalTask) {
        return new Project();
    }
}
