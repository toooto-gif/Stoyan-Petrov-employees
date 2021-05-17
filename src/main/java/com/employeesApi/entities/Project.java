package main.java.com.employeesApi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project {
    private Long id;
    private List<Employee> employees;

    public Project(Long id) {
        this.id = id;
        this.employees = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public boolean addEmployee(Employee employee) {
        return this.employees.add(employee);
    }
}
