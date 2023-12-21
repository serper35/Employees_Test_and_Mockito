package pro.sky.Employes.Service;


import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public Employees addEmployee(String name, String lastName,  int department, double salary);
    public String removeEmployee(String name, String lastName);
    public String findEmployee(String name, String lastName);

    public Map<String, Employees> getInfo();
}
