package ru.job4j.bank;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 29.04.2018
 */
public class Account {
    private double value;
    private String requsites;

    public double getValue() {
        return value;
    }

    public String getRequsites() {
        return requsites;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRequsites(String requsites) {
        this.requsites = requsites;
    }
}
