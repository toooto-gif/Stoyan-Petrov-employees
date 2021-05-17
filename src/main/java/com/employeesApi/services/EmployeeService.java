package main.java.com.employeesApi.services;

import java.io.File;
import java.util.Map.Entry;
import main.java.com.employeesApi.models.EmployeePair;
import main.java.com.employeesApi.models.WorkedTime;

public interface EmployeeService {
    Entry<EmployeePair, WorkedTime> getEmployeesFromFile(File file);
}
