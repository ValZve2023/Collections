package ru.skypro.CollectionsHomework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.CollectionsHomework.model.Employee;
import ru.skypro.CollectionsHomework.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("add")
    public Employee add(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname) {
        return employeeService.add(firstname, lastname);
    }

    @GetMapping("remove")
    public Employee remove(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname) {
        return employeeService.remove(firstname, lastname);
    }

    @GetMapping("find")
    public Employee find(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname) {
        return employeeService.find(firstname, lastname);
    }

    @GetMapping
    public Map<String, Employee> getAll() {
        return employeeService.getAll();
    }
}

