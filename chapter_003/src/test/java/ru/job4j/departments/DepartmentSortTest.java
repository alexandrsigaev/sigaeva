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

    private List<Department> departmentsSrc = new ArrayList<>();
    private List<Department> departmentsSortAscending = new ArrayList<>();
    private List<Department> departmentsSortDescending = new ArrayList<>();


    @Before
    public void setUp() {
        departmentsSrc.add(new Department("K1"));
        departmentsSrc.add(new Department("K2"));
        departmentsSrc.add(new Department("K1\\SK1\\SSK1"));
        departmentsSrc.add(new Department("K4\\SK1\\"));
        departmentsSrc.add(new Department("K1\\SK1\\SSK2"));
        departmentsSrc.add(new Department("K3\\SK1\\SSK1"));
        departmentsSrc.add(new Department("K2\\SK1\\SSK1"));

        departmentsSortAscending.add(new Department("K1"));
        departmentsSortAscending.add(new Department("K1\\SK1"));
        departmentsSortAscending.add(new Department("K1\\SK1\\SSK1"));
        departmentsSortAscending.add(new Department("K1\\SK1\\SSK2"));
        departmentsSortAscending.add(new Department("K2"));
        departmentsSortAscending.add(new Department("K2\\SK1"));
        departmentsSortAscending.add(new Department("K2\\SK1\\SSK1"));
        departmentsSortAscending.add(new Department("K3"));
        departmentsSortAscending.add(new Department("K3\\SK1"));
        departmentsSortAscending.add(new Department("K3\\SK1\\SSK1"));
        departmentsSortAscending.add(new Department("K4"));
        departmentsSortAscending.add(new Department("K4\\SK1"));


        departmentsSortDescending.add(new Department("K4"));
        departmentsSortDescending.add(new Department("K4\\SK1"));
        departmentsSortDescending.add(new Department("K3"));
        departmentsSortDescending.add(new Department("K3\\SK1"));
        departmentsSortDescending.add(new Department("K3\\SK1\\SSK1"));
        departmentsSortDescending.add(new Department("K2"));
        departmentsSortDescending.add(new Department("K2\\SK1"));
        departmentsSortDescending.add(new Department("K2\\SK1\\SSK1"));
        departmentsSortDescending.add(new Department("K1"));
        departmentsSortDescending.add(new Department("K1\\SK1"));
        departmentsSortDescending.add(new Department("K1\\SK1\\SSK2"));
        departmentsSortDescending.add(new Department("K1\\SK1\\SSK1"));
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