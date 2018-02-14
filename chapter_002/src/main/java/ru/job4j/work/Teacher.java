package ru.job4j.work;

import java.util.GregorianCalendar;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.02.2018
 */
public class Teacher extends Profession {
    private String subject;
    private String[] schoolClass;

    public Teacher(String name, String surname, String patronymic, GregorianCalendar birthday) {
        super(name, surname, patronymic, birthday);
    }

    public void teach(Children children) {
        System.out.println("Учитель " + super.getName() + " " + super.getPatronymic() + " по " + this.subject +
                " проводит занятие для ученика из класса " + children.getSchoolClass());
    }

    public String getTask() {
        return "Выпольнить задания на стр. ...";
    }

}
