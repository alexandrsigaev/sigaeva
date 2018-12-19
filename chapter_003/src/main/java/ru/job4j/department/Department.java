package ru.job4j.department;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 19.12.2018
 */
public class Department {
    private final static String SEPARATOR = "\\\\";
    private List<String> departments;

    public Department(String departments) {
        this.departments = Arrays.asList(departments.split(SEPARATOR));
    }

    public List<String> getDepartments() {
        return this.departments;
    }

    public int getSize() {
        return this.departments.size();
    }

    public String getDepart(int index) {
        return departments.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        Department that = (Department) o;
        return Objects.equals(getDepartments(), that.getDepartments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartments());
    }

    @Override
    public String toString() {
        return "Department{" + "departments=" + departments + '}';
    }
}
