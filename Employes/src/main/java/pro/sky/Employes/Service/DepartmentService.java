package pro.sky.Employes.Service;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employees minSalaryEmployee(int dep);
    Employees maxSalaryEmployee(int dep);

    Map<Integer,List<Employees>> allEmployees();
    List<Employees> employeesByDep(int dep);

    double sumOfSalaryByDep(int dep);
}
