package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 30.08.2018
 */
public class SimpleHashMap<K, V> implements Iterable<K> {

    private static final int DEFAULT_HASH_TABLE_SIZE = 5;
    private static final float DEFAULT_LOAD_FACTORY = 0.75f;

    private Node<K, V>[] hashTable;
    private int hashTableSize;
    private float loadFactor = 0;
    private int size = 0;
    private int modCount = 0;

    public SimpleHashMap() {
        this.hashTableSize = DEFAULT_HASH_TABLE_SIZE;
        this.hashTable = new Node[this.hashTableSize];
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        int hash = this.hash(key);
        if (this.loadFactor > DEFAULT_LOAD_FACTORY) {
            this.increase();
        }
        if (this.hashTable[hash] == null) {
            this.hashTable[hash] = new Node<>(key, value);
            this.loadFactor = (float) ++size / this.hashTableSize;
            this.modCount++;
            result = true;
        }
        return result;
    }

    public V get(K key) {
        int hash = this.hash(key);
        Node<K, V> tmp = this.hashTable[hash];
        return tmp != null && tmp.key.equals(key) ? tmp.value : null;
    }

    public boolean delete(K key) {
        boolean result = false;
        int hash = this.hash(key);
        if (this.hashTable[hash] != null && this.hashTable[hash].key.equals(key)) {
            this.hashTable[hash] = null;
            this.loadFactor = (float) --size / this.hashTableSize;
            this.modCount++;
            result = true;
        }
        return result;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return key.hashCode() % this.hashTableSize;
    }

    private void increase() {
        this.hashTableSize *=  2;
        Node<K, V> [] newHashTable = new Node[this.hashTableSize];
        for (Node<K, V> node : hashTable) {
            if (node != null) {
                newHashTable[hash(node.key)] = node;
            }
        }
        this.hashTable = newHashTable;

    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedMpdCount = modCount;
            int iterCount = 0;
            int sizeCount = 0;
            @Override
            public boolean hasNext() {
                if (this.expectedMpdCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return this.sizeCount < size;
            }

            @Override
            public K next() {
                K result = null;
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                while (this.iterCount < hashTableSize && hashTable[iterCount] == null) {
                    iterCount++;
                }
                result = hashTable[iterCount++].key;
                sizeCount++;
                return result;
            }
        };
    }

    static class Node<K, V> {
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
