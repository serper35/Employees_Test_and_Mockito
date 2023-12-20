package pro.sky.Employes.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Employes.Service.DepartmentService;
import pro.sky.Employes.Service.Employees;

import java.util.List;
import java.util.Map;

@RequestMapping("/departments")
@RestController
public class ControllerDepartments {
    private final DepartmentService departmentService;

    public ControllerDepartments(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employees maxSalary(@RequestParam("departmentId") int id) {
        return departmentService.maxSalaryEmployee(id);
    }

    @GetMapping("/min-salary")
    public Employees minSalary(@RequestParam("departmentId") int id) {
        return departmentService.minSalaryEmployee(id);
    }

    @GetMapping(value ="/all", params = "departmentId")
    public List<Employees> empByDep(@RequestParam("departmentId") int id){
        return departmentService.employeesByDep(id);
    }

    @GetMapping("/all")
    public Map<Integer,List<Employees>> allEmployyes(){
        return departmentService.allEmployees();
    }
}
