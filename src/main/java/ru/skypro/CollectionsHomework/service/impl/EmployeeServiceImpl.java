package ru.skypro.CollectionsHomework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.CollectionsHomework.exception.EmployeeAlreadyAddedException;
import ru.skypro.CollectionsHomework.exception.EmployeeNotFoundException;
import ru.skypro.CollectionsHomework.exception.EmployeeStorageIsFullException;
import ru.skypro.CollectionsHomework.model.Employee;
import ru.skypro.CollectionsHomework.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int limit = 5;
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String firstname, String lastname) {
        if (employees.size() >= limit) {
            throw new EmployeeStorageIsFullException(
                    "Превышено допустимое количество сотрудников! Допустимое количество: " + limit);
        }

        if (employees.containsKey(getKey(firstname, lastname))) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует!");
        }
        Employee employee = new Employee(firstname, lastname);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstname, String lastname) {
        if (!employees.containsKey(getKey(firstname, lastname))) {
            throw new EmployeeNotFoundException("Сотрудник " + firstname + " " + lastname + " не найден!");
        }
        return employees.remove(getKey(firstname, lastname));
    }

    @Override
    public Employee find(String firstname, String lastname) {
        Employee employee = employees.get(getKey(firstname, lastname));
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник " + firstname + " " + lastname + " не найден!");
        }
        return employee;
    }

    @Override
    public Map<String, Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }

    private static String getKey(String firstname, String lastname) {
        return firstname + lastname;
    }

    private static String getKey(Employee employee) {
        return employee.getFirstname() + employee.getLastname();
    }
}
