package ru.job4j.departments;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.department.Department;
import ru.job4j.department.DepartmentSort;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 19.12.2018
 */
public class DepartmentSortTest {

    private List<Department> departmentsSrc;
    private List<Department> departmentsSortAscending;
    private List<Department> departmentsSortDescending;


    @Before
    public void setUp() {
        this.departmentsSrc = new ArrayList<>(List.of(
                new Department("K1"),
                new Department("K1\\SK1\\SSK1"),
                new Department("K4\\SK1\\"),
                new Department("K1\\SK1\\SSK2"),
                new Department("K3\\SK1\\SSK1"),
                new Department("K2\\SK1\\SSK1")
        ));

        this.departmentsSortAscending = new ArrayList<>(List.of(
                new Department("K1"),
                new Department("K1\\SK1"),
                new Department("K1\\SK1\\SSK1"),
                new Department("K1\\SK1\\SSK2"),
                new Department("K2"),
                new Department("K2\\SK1"),
                new Department("K2\\SK1\\SSK1"),
                new Department("K3"),
                new Department("K3\\SK1"),
                new Department("K3\\SK1\\SSK1"),
                new Department("K4"),
                new Department("K4\\SK1")
        ));

        this.departmentsSortDescending = new ArrayList<>(List.of(
                new Department("K4"),
                new Department("K4\\SK1"),
                new Department("K3"),
                new Department("K3\\SK1"),
                new Department("K3\\SK1\\SSK1"),
                new Department("K2"),
                new Department("K2\\SK1"),
                new Department("K2\\SK1\\SSK1"),
                new Department("K1"),
                new Department("K1\\SK1"),
                new Department("K1\\SK1\\SSK2"),
                new Department("K1\\SK1\\SSK1")
        ));
    }

    @Test
    public void whenAscendingSortDepartments() {
        departmentsSrc = DepartmentSort.sortAscending(departmentsSrc);
        assertThat(departmentsSrc, is(departmentsSortAscending));
    }

    @Test
    public void whenDescendingSortDepartments() {
        departmentsSrc = DepartmentSort.sortDescending(departmentsSrc);
        assertThat(departmentsSrc, is(departmentsSortDescending));
    }


}