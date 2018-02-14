package ru.job4j.work;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 13.02.2018
 */
public class Project {
    private int nubber;
    private int discription;
    private Engineer designer;

    public int getNubber() {
        return nubber;
    }

    public void convertInPdf() {
        System.out.println("Проект № " + this.nubber + " сконвертирован в PDF файл");
    }
}
