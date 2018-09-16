package ru.job4j.tree;

import java.util.*;

/**
 * Class Tree
 * @author sigaevaleksandr
 * @since 31.08.2018
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    private int size = 0;
    private int modCount = 0;

    public Tree(E value) {
        this.root = new Node<>(value);
        this.size++;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> tmp = this.findBy(parent);
        if (tmp.isPresent() && !this.findBy(child).isPresent()) {
            tmp.get().add(new Node<>(child));
            result = true;
            this.size++;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        return this.findIsBinary(this.root);
    }

    private boolean findIsBinary(Node<E> root) {
        boolean isBinary = true;
        for (int i = 0; i < root.leaves().size() && isBinary; i++) {
            findIsBinary(root.leaves().get(i));
        }
        if (root.leaves().size() > 2) {
            isBinary = false;
        }
        return isBinary;
    }
    

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int count = 0;
            private int expectedModCount = modCount;
            private List<Node<E>> listOfAll = new LinkedList<>();

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (count == 0) {
                    this.fillListOfTheNodes(root);
                }
                return this.count < size;
            }

            @Override
            public E next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return this.listOfAll.get(count++).getValue();
            }

            private void fillListOfTheNodes(Node<E> first) {
                for (int i = 0; i < first.leaves().size(); i++) {
                    this.fillListOfTheNodes(first.leaves().get(i));
                }
                this.listOfAll.add(first);
            }
        };
    }
}
