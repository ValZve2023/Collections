package ru.skypro.CollectionsHomework.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {
    private final String firstname;
    private final String lastname;
    private final int salary;
    private final int department;

    public Employee(String firstname, String lastname, int salary, int department) {
        this.firstname = StringUtils.capitalize(firstname);
        this.lastname = StringUtils.capitalize(lastname);
        this.salary = salary;
        this.department = department;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && department == employee.department && Objects.equals(firstname, employee.firstname) && Objects.equals(lastname, employee.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, salary, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
