package ru.job4j.work;

import java.util.GregorianCalendar;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.02.2018
 */
public class Profession {
    private String name;
    private String surname;
    private String patronymic;
    private GregorianCalendar birthday;
    private int workExperience;
    private String placeOfWork;
    private String education;
    private int diplomNumber;


    public Profession(String name, String surname, String patronymic, GregorianCalendar birthday) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public GregorianCalendar getBirthday() {
        return birthday;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public String getEducation() {
        return education;
    }

    public int getDiplomNumber() {
        return diplomNumber;
    }

    public void goWork() {

    }


}
