package pro.sky.Employes.Controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.Employes.Service.EmployeeService;
import pro.sky.Employes.Service.Employees;

import java.util.Map;

@RequestMapping("employee")
@RestController
public class ControllerEmployees {

    private final EmployeeService employeeService;

    public ControllerEmployees(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/add")
    public String add(@RequestParam("firstName") String name,
                      @RequestParam("lastName") String lastname,
                      @RequestParam("department") int department,
                      @RequestParam("salary") double salary ) {
        return "Сотрудник " + employeeService.addEmployee(name, lastname, department, salary) + " добавлен.";
    }

    @GetMapping("/getInfo")
    public Map<String, Employees> getInfo() {
        return employeeService.getInfo();
    }

    @GetMapping("remove")
    public String remove(@RequestParam("firstName") String name, @RequestParam("lastName") String lastname) {
        return employeeService.removeEmployee(name, lastname);
    }

    @GetMapping("find")
    public String find(@RequestParam("firstName") String name, @RequestParam("lastName") String lastname) {
        return employeeService.findEmployee(name, lastname);
    }
}
