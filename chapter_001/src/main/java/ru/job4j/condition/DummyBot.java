package ru.job4j.condition;

/**
 *Class DummyBot.
 *@author Sigaev Aleksandr (sigaev.aleksandr.v@yandex.ru)
 *@version 1.0
 *@since 26.01.2018
 */
public class DummyBot {
    /**
     * Отвечает на вопросы.
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer (String question) {
        String srl = "Это ставит меня в тупик";
        if ("Привет, Бот".equals(question)) {
            srl = "Привет, умник";
        }else if ("Пока.".equals(question)) {
            srl = "До скорой встречи.";
        }
        return srl;
    }
}
