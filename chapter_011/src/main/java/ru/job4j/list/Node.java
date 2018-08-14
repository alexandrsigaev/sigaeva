package ru.job4j.list;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 11.08.2018
 */
public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public static boolean hasCycle(Node first) {
        boolean result = false;
        Node prev = first;
        Node foll = first;
        while (foll != null && foll.next != null) {
            prev = prev.next;
            foll = foll.next.next;
            if (prev == foll) {
                result = true;
                break;
            }
        }
        return result;
    }
}
