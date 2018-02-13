package ru.job4j.work;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.02.2018
 */
public class Doctor extends Profession {
    private String specialization;

    public void cure(Patient patient) {

    }

    public Calendar getAppointment(Patient patient) {
        GregorianCalendar appoitment = new GregorianCalendar();
        appoitment.add(Calendar.DAY_OF_MONTH, 2);
        return appoitment;
    }

    public String writingTheRecipe(Patient patient) {
        return "Вот ваш рецепт";
    }
}
