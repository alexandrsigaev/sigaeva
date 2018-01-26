package ru.job4j.calculator;

/**
 *Class Calculator производит арифметические операции.
 *@author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 *@version 1.0
 *@since 26.01.2018
 */
public class Calculator {

    private double result;

    public void add (double first, double second) {
        this.result = first + second;
    }

    public void subtract (double first, double second) {
        this.result = first - second;
    }

    public void multiplication (double first, double second) {
        this.result = first * second;
    }

    public void div (double first, double second) {
        this.result = first / second;
    }

    public double getResult() {
        return this.result;
    }

}
