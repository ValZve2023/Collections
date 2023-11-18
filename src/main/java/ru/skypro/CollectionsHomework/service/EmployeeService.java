package ru.skypro.CollectionsHomework.service;

import ru.skypro.CollectionsHomework.model.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee add(String firstname, String lastname);
    Employee remove(String firstname, String lastname);
    Employee find(String firstname, String lastname);

    Map<String, Employee> getAll();
}

