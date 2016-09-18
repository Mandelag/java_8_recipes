package optionals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HRTest {
    private HR hr = HR.getInstance();
    private List<Employee> sampleEmployees = Arrays.asList(
            new Employee("Malcolm Reynolds"),
            new Employee("Zoe Washburne"),
            new Employee("Hoban Washburne"),
            new Employee("Jayne Cobb"),
            new Employee("Kaylee Frye"));

    @Before
    public void setUp() {
        hr.hire(sampleEmployees);
    }

    @After
    public void tearDown() {
        hr.reset();
    }

    @Test
    public void hireNotNull() throws Exception {
        int id = hr.hire(new Employee("River Tam"));
        assertTrue(hr.findEmployeeById(id).isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void hireNull() {
        hr.hire((Employee) null);
    }

    @Test
    public void findEmployeeById() throws Exception {
        sampleEmployees
                .forEach(e -> assertTrue(hr.findEmployeeById(e.getId()).isPresent()));
    }

    @Test
    public void findEmployeesByIds1() throws Exception {
        List<Integer> ids = sampleEmployees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        ids.add(99);

        assertFalse(hr.findEmployeeById(99).isPresent());

        List<Employee> emps = hr.findEmployeesByIds1(ids);
        assertEquals(sampleEmployees.size(), emps.size());
        sampleEmployees.forEach(e -> assertTrue(emps.contains(e)));
    }

    @Test
    public void findEmployeesByIds2() throws Exception {
        List<Integer> ids = sampleEmployees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        ids.add(86);

        assertFalse(hr.findEmployeeById(86).isPresent());

        List<Employee> emps = hr.findEmployeesByIds1(ids);
        assertEquals(sampleEmployees.size(), emps.size());
        sampleEmployees.forEach(e -> assertTrue(emps.contains(e)));
    }

}