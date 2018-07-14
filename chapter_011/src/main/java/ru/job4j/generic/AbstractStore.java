package ru.job4j.generic;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 12.07.2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> sourse;
    private static final int DEFAULT_STORE_SIZE = 10;

    public AbstractStore() {
        this.sourse = new SimpleArray<>(DEFAULT_STORE_SIZE);
    }

    public AbstractStore(int size) {
        this.sourse = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        this.sourse.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int tmp = this.findIndexElemById(id);
        if (tmp != -1) {
            result = true;
            this.sourse.set(tmp, model);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int tmp = this.findIndexElemById(id);
        if (tmp != -1) {
            result = true;
            this.sourse.delete(tmp);
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int tmp = this.findIndexElemById(id);
        if (tmp != -1) {
            result = this.sourse.get(tmp);
        }
        return result;
    }

    private int findIndexElemById(String id) {
        int result = -1;
        int count = 0;
        for (T elem : sourse) {
            if (id.equals(elem.getId())){
                result = count;
                break;
            }
            count++;
        }
        return result;
    }
}
