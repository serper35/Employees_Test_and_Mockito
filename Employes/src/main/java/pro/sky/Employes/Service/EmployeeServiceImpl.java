package pro.sky.Employes.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.Employes.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.Employes.Exceptions.EmployeeBadRequestException;
import pro.sky.Employes.Exceptions.EmployeeNotFoundException;
import pro.sky.Employes.Exceptions.EmployeeStorageIsFullException;


import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int WORKER_SIZE = 10;

    Map<String, Employees> workers = new HashMap<>(Map.of(
            "VladVolkov",
            new Employees("Vlad", "Volkov", 1, 100000),
            "EgorGolovanov",
            new Employees("Egor", "Golovanov", 2, 50000),
            "FrankLampard",
            new Employees("Frank", "Lampard", 1, 60000),
            "JohnTerry",
            new Employees("John", "Terry", 2, 70000),
            "PetrChech",
            new Employees("Petr", "Chech", 1, 80000),
            "DidierDrogba",
            new Employees("Didier", "Drogba", 2, 90000)
    ));

    @Override
    public Employees addEmployee(String name, String lastName, int department, double salary) {

        Employees employees = new Employees(name, lastName, department, salary);

        if (workers.containsKey(name + lastName)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже есть в базе данных.");
        }

        boolean checkName = StringUtils.isAlpha(name + lastName);

        if (!checkName) {
            throw new EmployeeBadRequestException("В имени и фамилии могут содержаться только буквы");
        }

        if (workers.size() < WORKER_SIZE) {
            workers.put(name + lastName, employees);
            return employees;
        } else {
            throw new EmployeeStorageIsFullException("База данных переполнена.");
        }
    }

    @Override
    public String removeEmployee(String name, String lastName) {

        if (workers.containsKey(name + lastName)) {
            workers.remove(name + lastName);
            return "Сотрудник " + name + " " + lastName + " удален.";
        }

        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public String findEmployee(String name, String lastName) {

        if (workers.containsKey(name + lastName)) {
            return "Сотрудник " + name + " " + lastName  + " найден.";
        }

        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    public Map<String, Employees> getInfo() {
        return Collections.unmodifiableMap(workers);
    }
}
