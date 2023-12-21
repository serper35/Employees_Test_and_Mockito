package pro.sky.Employes.Service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;


    @Test
    void shouldGetminSalaryEmployeeCorectly() {
        Employees employees1 = new Employees("Oleg", "Ivanov", 1, 100);
        Employees employees2 = new Employees("Ivan", "Petrov", 1, 10000);
        Map <String, Employees> testMap = new HashMap<>(Map.of("1", employees1, "2", employees2));

        Mockito.when(employeeService.getInfo()).thenReturn(testMap);

        Employees actual = departmentService.minSalaryEmployee(1);

        assertEquals(employees1,actual);
    }

    @Test
    void shouldThrowExceptionWhenGetMinSalaryEmployeeMapIsNull() {
        Map <String, Employees> testMap = new HashMap<>();

        Mockito.when(employeeService.getInfo()).thenReturn(testMap);

        assertThrows(NoSuchElementException.class, () -> departmentService.minSalaryEmployee(1));
    }

    @Test
    void shouldThrowExceptionWhenGetMinSalaryEmployeeHasWrongNumberOfDepartment() {
        Employees employees1 = new Employees("Oleg", "Ivanov", 1, 100);
        Employees employees2 = new Employees("Ivan", "Petrov", 1, 10000);
        Map <String, Employees> testMap = new HashMap<>(Map.of("1", employees1, "2", employees2));

        Mockito.when(employeeService.getInfo()).thenReturn(testMap);

        assertThrows(NoSuchElementException.class, () -> departmentService.minSalaryEmployee(3));
    }

    @Test
    void maxSalaryEmployee() {
        Employees employees1 = new Employees("Oleg", "Ivanov", 1, 100);
        Employees employees2 = new Employees("Ivan", "Petrov", 1, 10000);
        Map <String, Employees> testMap = new HashMap<>(Map.of("1", employees1, "2", employees2));

        Mockito.when(employeeService.getInfo()).thenReturn(testMap);

        Employees actual = departmentService.maxSalaryEmployee(1);

        assertEquals(employees2, actual);
    }

    @Test
    void shouldThrowExceptionWhenGetMaxSalaryEmployeeMapIsNull() {
        Map <String, Employees> testMap = new HashMap<>();

        Mockito.when(employeeService.getInfo()).thenReturn(testMap);

        assertThrows(NoSuchElementException.class, () -> departmentService.maxSalaryEmployee(1));
    }

    @Test
    void shouldThrowExceptionWhenGetMaxSalaryEmployeeHasWrongNumberOfDepartment() {
        Employees employees1 = new Employees("Oleg", "Ivanov", 1, 100);
        Employees employees2 = new Employees("Ivan", "Petrov", 1, 10000);
        Map <String, Employees> testMap = new HashMap<>(Map.of("1", employees1, "2", employees2));

        Mockito.when(employeeService.getInfo()).thenReturn(testMap);

        assertThrows(NoSuchElementException.class, () -> departmentService.maxSalaryEmployee(3));
    }

    @Test
    void allEmployees() {
        Employees employees1 = new Employees("Oleg", "Ivanov", 1, 100);
        Employees employees2 = new Employees("Ivan", "Petrov", 1, 10000);
        Map <String, Employees> testMap = new HashMap<>(Map.of("1", employees1, "2", employees2));

        Mockito.when(employeeService.getInfo()).thenReturn(testMap);
        Map<Integer, List<Employees>> expected = new HashMap<>((Map.of(1, new ArrayList<>(testMap.values()))));


        Map<Integer, List<Employees>> actual = departmentService.allEmployees();

        assertEquals(expected,actual);
    }

    @Test
    void employeesByDep() {
        Employees employees1 = new Employees("Oleg", "Ivanov", 1, 100);
        Employees employees2 = new Employees("Ivan", "Petrov", 1, 10000);
        Map <String, Employees> testMap = new HashMap<>(Map.of("1", employees1, "2", employees2));
        Mockito.when(employeeService.getInfo()).thenReturn(testMap);

        List<Employees> expected = new ArrayList<>(testMap.values());
        List<Employees> actual = departmentService.employeesByDep(1);

        assertEquals(expected, actual);
    }

    @Test
    void sumOfSalaryByDep() {
        Employees employees1 = new Employees("Oleg", "Ivanov", 1, 100);
        Employees employees2 = new Employees("Ivan", "Petrov", 1, 10000);
        Map <String, Employees> testMap = new HashMap<>(Map.of("1", employees1, "2", employees2));
        Mockito.when(employeeService.getInfo()).thenReturn(testMap);

        double actual = departmentService.sumOfSalaryByDep(1);

        assertEquals(10100,actual);

    }
}