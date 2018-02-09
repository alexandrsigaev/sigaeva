package ru.job4j.array;

/**
 * @author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 * @version 1.0
 * @since 04.02.2018
 */
public class Parser {
    /**
     * Checks whether the sub line appears in the string original.
     * @param original the original string in which the search is performed
     * @param sub required substring
     * @return there was a conformity or not
     */
    public boolean contains(String original, String sub) {
        boolean result = false;
        char[] arrOriginStr = original.toCharArray();
        char[] arrSubStr = sub.toCharArray();
        int countOfSubStr = 0;
        for (int countOfOriginSub = 0; countOfOriginSub < arrOriginStr.length; countOfOriginSub++) {
            if (arrOriginStr[countOfOriginSub] == arrSubStr[countOfSubStr] && countOfSubStr < arrSubStr.length) {
                countOfSubStr++;
                if (countOfSubStr == arrSubStr.length - 1) {
                    result = true;
                    break;
                }
            } else {
                countOfSubStr = 0;
            }
        }
        return result;
    }
}
