package ru.job4j.nonblock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.11.2018
 */
@ThreadSafe
public class Cache {

    @GuardedBy("this")
    private final ConcurrentHashMap<Integer, Base> data;

    public Cache() {
        this.data = new ConcurrentHashMap<>();
    }

    public Base add(Base model) {
        return data.putIfAbsent(model.getId(), model);
    }

    public boolean update(Base model) {
       boolean result = false;
       data.computeIfPresent(model.getId(), (integer, base) -> {
           if (base.getVersion() != model.getVersion()) {
               throw new OptimisticException("Change denied");
           }
           base.setName(model.getName());
           base.setVersion(model.getVersion() + 1);
           return base;
       });
       result = true;
       return result;
    }

    public Base delete(Base model) {
        return data.remove(model.getId());
    }


    static class Base {

        private  int id;
        private  int version;
        private String name;

        public Base(int id, String name) {
            this.id = id;
            this.version = 0;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public int getVersion() {
            return version;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Base)) {
                return false;
            }
            Base base = (Base) o;
            return getId() == base.getId()
                    && getVersion() == base.getVersion()
                    && Objects.equals(getName(), base.getName());
        }

        @Override
        public int hashCode() {

            return Objects.hash(getId(), getVersion(), getName());
        }
    }
}
