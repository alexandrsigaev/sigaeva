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
    private List<Department> departmentsDist = new ArrayList<>();


    @Before
    public void setUp() {
        departmentsSrc.add(new Department("K1"));
        departmentsSrc.add(new Department("K2"));
        departmentsSrc.add(new Department("K1\\SK1\\SSK1"));
        departmentsSrc.add(new Department("K4\\SK1\\"));
        departmentsSrc.add(new Department("K1\\SK1\\SSK2"));
        departmentsSrc.add(new Department("K3\\SK1\\SSK1"));
        departmentsSrc.add(new Department("K2\\SK1\\SSK1"));

        departmentsDist.add(new Department("K1"));
        departmentsDist.add(new Department("K1\\SK1"));
        departmentsDist.add(new Department("K1\\SK1\\SSK1"));
        departmentsDist.add(new Department("K1\\SK1\\SSK2"));
        departmentsDist.add(new Department("K2"));
        departmentsDist.add(new Department("K2\\SK1"));
        departmentsDist.add(new Department("K2\\SK1\\SSK1"));
        departmentsDist.add(new Department("K3"));
        departmentsDist.add(new Department("K3\\SK1"));
        departmentsDist.add(new Department("K3\\SK1\\SSK1"));
        departmentsDist.add(new Department("K4"));
        departmentsDist.add(new Department("K4\\SK1\\"));
    }

    @Test
    public void whenSortDepartments() {
        departmentsSrc = DepartmentSort.sort(departmentsSrc);
        assertThat(departmentsSrc, is(departmentsDist));
    }


}