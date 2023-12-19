package ru.skypro.CollectionsHomework.service.impl;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.CollectionsHomework.exception.EmployeeAlreadyAddedException;
import ru.skypro.CollectionsHomework.exception.EmployeeNotFoundException;
import ru.skypro.CollectionsHomework.exception.EmployeeStorageIsFullException;
import ru.skypro.CollectionsHomework.exception.InvalidNameException;
import ru.skypro.CollectionsHomework.model.Employee;
import ru.skypro.CollectionsHomework.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int limit = 5;

    @PostConstruct
    public void initEmployees() {
        add("Ivan", "Ivanov", 45000, 1);
        add("Dmitriy", "Kuplinov", 80000, 2);
        add("Vasya", "Pupkin", 60000, 1);
        add("Sergeev", "Sergey", 50000, 2);
    }

    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String firstname, String lastname, Integer salary, Integer department) {
        validateNames(firstname, lastname);
        if (employees.size() >= limit) {
            throw new EmployeeStorageIsFullException(
                    "Превышено допустимое количество сотрудников! Допустимое количество: " + limit);
        }

        if (employees.containsKey(getKey(firstname, lastname))) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует!");
        }
        Employee employee = new Employee(firstname, lastname, salary, department);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstname, String lastname) {
        validateNames(lastname, firstname);
        if (!employees.containsKey(getKey(firstname, lastname))) {
            throw new EmployeeNotFoundException("Сотрудник " + firstname + " " + lastname + " не найден!");
        }
        return employees.remove(getKey(firstname, lastname));
    }

    @Override
    public Employee find(String firstname, String lastname) {
        validateNames(firstname, lastname);
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
        return (firstname + "_" + lastname).toLowerCase();
    }

    private static String getKey(Employee employee) {
        return employee.getFirstname() + employee.getLastname();
    }

    private void validateNames(String... names) {
        for (String name : names) {
            if (!StringUtils.isAlpha(name)) {
                throw new InvalidNameException(name + " is invalid");
            }
        }
    }
}
