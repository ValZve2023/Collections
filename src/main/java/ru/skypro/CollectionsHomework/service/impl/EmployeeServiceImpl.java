package ru.skypro.CollectionsHomework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.CollectionsHomework.exception.EmployeeAlreadyAddedException;
import ru.skypro.CollectionsHomework.exception.EmployeeNotFoundException;
import ru.skypro.CollectionsHomework.exception.EmployeeStorageIsFullException;
import ru.skypro.CollectionsHomework.model.Employee;
import ru.skypro.CollectionsHomework.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int limit = 5;
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee add(String firstname, String lastname) {
        if (employees.size() >= limit) {
            throw new EmployeeStorageIsFullException("Превышено допустимое количество сотрудников!");
        }
        Employee employee = new Employee(firstname, lastname);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует!");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstname, String lastname) {
        Employee employee = new Employee(firstname, lastname);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник " + firstname + " " + lastname + " не найден!");
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstname, String lastname) {
        Employee needeEmployee = new Employee(firstname, lastname);
        for (Employee employee : employees) {
            if (employee.equals(needeEmployee)) {
                return needeEmployee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник " + firstname + " " + lastname + " не найден!");
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
