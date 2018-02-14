package ru.job4j.work;

/**
 * Class
 * @author sigaevaleksandr
 * @since 12.02.2018
 */
public class Patient {
    private int socialSecurityNumber;

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void visitDoctor() {
        System.out.println("Пациент идет на прием к врачу");
    }

    public Medicament buyMedicaments(String recipe) {
        return new Medicament();
    }
}
