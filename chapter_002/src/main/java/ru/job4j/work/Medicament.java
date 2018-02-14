package ru.job4j.work;

/**
 * Class
 * @author sigaevaleksandr
 * @since 13.02.2018
 */
public class Medicament {
    private String name;
    private String placeOfProduction;
    private String manufacturer;

    public Medicament() {
    }

    public Medicament(String name, String placeOfProduction, String manufacturer) {
        this.name = name;
        this.placeOfProduction = placeOfProduction;
        this.manufacturer = manufacturer;
        addToBase(this);
    }

    private void addToBase(Medicament medicament) {
        System.out.println("Медикамент " + medicament.name + " добавлен в список");
    }
}
