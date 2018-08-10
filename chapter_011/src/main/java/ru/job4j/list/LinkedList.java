package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 22.07.2018
 */
public class LinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;
    private int modCount = 0;

    public void add(E value) {
        Node<E> newLink = new Node<>(value);
        if (size == 0) {
            this.head = newLink;
            this.tail = newLink;
        } else {
            this.tail.next = newLink;
        }
        this.tail = newLink;
        this.size++;
        this.modCount++;

    }

    public E get(int index) {
        Node<E> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedMpdCount = modCount;
            int iterCount = 0;
            Node<E> tmp = head;
            @Override
            public boolean hasNext() {
                if (expectedMpdCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                return size > iterCount;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = tmp.data;
                this.tmp = tmp.next;
                return result;
            }
        };
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;

        public Node(E data) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
}

