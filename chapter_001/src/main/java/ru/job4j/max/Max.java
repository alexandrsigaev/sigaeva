package ru.job4j.max;

/**
 *Class Max.
 *@author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 *@version 1.0
 *@since 26.01.2018
 */
public class Max {

    public int max(int first, int second) {
        return first > second ? first : second;
    }

    public int max(int first, int second, int third) {
        return max(first, second) > third ? max(first, second) : third;
    }
}
