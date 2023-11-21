package ru.skypro.CollectionsHomework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.CollectionsHomework.model.Employee;
import ru.skypro.CollectionsHomework.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }
    @GetMapping(value = "All", params = "departmentId")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.getAllEmployeesByDepartment(departmentId);
    }
    @GetMapping("All")
    public Map<Integer, List<Employee>> getAllEmployees() {
        return departmentService.getAllEmployees();
    }
}
