package ru.job4j.work;

import java.util.GregorianCalendar;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.02.2018
 */
public class Engineer extends Profession {
    private int category;
    private String field;

    public Engineer(String name, String surname, String patronymic, GregorianCalendar birthday) {
        super(name, surname, patronymic, birthday);
    }

    public Project makeProject(String technicalTask) {
        Project development = new Project();
        System.out.println("Инженер " + super.getName() + " работает над проектом № " + development.getNubber());
        return development;
    }
}
