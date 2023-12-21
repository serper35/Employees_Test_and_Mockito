package pro.sky.Employes.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.Employes.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.Employes.Exceptions.EmployeeBadRequestException;
import pro.sky.Employes.Exceptions.EmployeeNotFoundException;
import pro.sky.Employes.Exceptions.EmployeeStorageIsFullException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    String name = "Oleg";
    String lastName = "Ivanov";
    int id = 1;
    double salary = 10000;

    @Test
    void shouldAddEmployeeCorectly() {
        Employees expected = new Employees(name, lastName, id, salary);
        Employees actual = employeeService.addEmployee(name, lastName, id, salary);
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeAlreadyAdded() {
        employeeService.addEmployee(name, lastName, id, salary);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(name, lastName, id, salary));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNotAlpha() {
        name = "vasya11";
        assertThrows(EmployeeBadRequestException.class, () -> employeeService.addEmployee(name, lastName, id, salary));
    }

    @Test
    void shouldThrowExceptionWhenDatabaseIsFull() {
        employeeService.addEmployee(name, "werwervwr", id, salary);
        employeeService.addEmployee("dfg", "lastName", id, salary);
        employeeService.addEmployee("name", "lastName", id, salary);
        employeeService.addEmployee("sdf", "sfsd", id, salary);

        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee(name, lastName, id, salary));
    }



    @Test
    void shouldRemoveEmployeeCorectly() {
        Employees expected =  employeeService.addEmployee(name, lastName, id, salary);
        String actual = employeeService.removeEmployee(expected.getFirstName(),expected.getLastName());
        assertEquals(("Сотрудник " + name + " " + lastName + " удален."), actual);

    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFoundRemoveMethod() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(name, lastName));
    }

    @Test
    void ShouldFindEmployeeCorectly() {
        employeeService.addEmployee(name, lastName, id, salary);
        String actual =  employeeService.findEmployee(name,lastName);
        assertEquals(("Сотрудник " + name + " " + lastName  + " найден."), actual);
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFoundFindMethod() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(name, lastName));
    }
}