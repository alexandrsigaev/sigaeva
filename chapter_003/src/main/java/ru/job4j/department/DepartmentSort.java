package ru.job4j.department;

import java.util.*;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 19.12.2018
 */
public class DepartmentSort {

    public static List<Department> sort(List<Department> src) {
        Comparator<Department> comparator = (o1, o2) -> {
            int result = 0;
            int min = Math.min(o1.getSize(), o2.getSize());
            for (int i = 0; i < min; i++) {
                if (!o1.getDepart(i).equals(o2.getDepart(i))) {
                    result = o1.getDepart(i).compareTo(o2.getDepart(i));
                }
            }
            if (result == 0) {
                result = o1.getSize() - o2.getSize();
            }
            return result;
        };
        List<Department> result = createCorrectStructure(src);
        result.sort(comparator);
        return result;
    }

    private static List<Department> createCorrectStructure(List<Department> src) {
        Set<Department> createDepart = new HashSet<>();
        for (Department depart : src) {
            StringBuilder builder = new StringBuilder();
            for (String part: depart.getDepartments()) {
                if (builder.length() != 0) {
                    builder.append("\\");
               }
                builder.append(part);
                createDepart.add(new Department(builder.toString()));
            }
        }
        return new ArrayList<>(createDepart);
    }

}
