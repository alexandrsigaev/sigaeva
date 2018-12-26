package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 25.12.2018
 */
public class Student implements Comparator<Student> {

    private String name;
    private int scope;

    public Student() {
    }

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o2.scope, o1.scope);
    }

    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().flatMap(Stream::ofNullable).sorted(this).
                takeWhile(student -> student.getScope() > bound).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return getScope() == student.getScope() && Objects.equals(getName(), student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getScope());
    }
}
