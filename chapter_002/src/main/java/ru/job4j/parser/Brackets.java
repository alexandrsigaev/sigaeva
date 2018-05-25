package ru.job4j.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 20.05.2018
 */
public class Brackets {
    private Map<Character, Character> bracketMap;

    public Brackets() {
        this.bracketMap = new HashMap<>();
    }

    public Map<Character, Character> getBracketMap() {
        return bracketMap;
    }

    public void add(char ... chars) {
        for (int i = 0; i < chars.length; i += 2) {
            bracketMap.put(chars[i], chars[i + 1]);
        }
    }
}
