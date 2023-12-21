package pro.sky.Employes.Service;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employees minSalaryEmployee(int dep) {
        return employeeService.getInfo().values().stream()
                .filter(e -> e.getDepartment() == dep)
                .min(Comparator.comparingDouble(emp -> emp.getSalary()))
                .orElseThrow();

    }

    @Override
    public Employees maxSalaryEmployee(int dep) {
        return employeeService.getInfo().values().stream()
                .filter(e -> e.getDepartment() == dep)
                .max(Comparator.comparingDouble(emp -> emp.getSalary()))
                .orElseThrow();
    }

    @Override
    public Map<Integer, List<Employees>> allEmployees() {
        return employeeService.getInfo().values().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
    }

    @Override
    public List<Employees> employeesByDep(int dep) {
        return employeeService.getInfo().values().stream()
                .filter(e -> e.getDepartment() == dep)
                .collect(Collectors.toList());
    }

    @Override
    public double sumOfSalaryByDep(int dep) {
        return employeeService.getInfo().values().stream()
                .filter(e -> e.getDepartment() == dep)
                .mapToDouble(e -> e.getSalary())
                .sum();
    }
}
