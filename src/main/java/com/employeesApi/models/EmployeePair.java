package main.java.com.employeesApi.models;

import java.util.Objects;
import main.java.com.employeesApi.entities.Employee;

public class EmployeePair {
    private Employee first;
    private Employee second;

    public Employee getFirst() {
        return first;
    }

    public void setFirst(Employee first) {
        this.first = first;
    }

    public Employee getSecond() {
        return second;
    }

    public void setSecond(Employee second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeePair that = (EmployeePair) o;
        return (Objects.equals(first, that.first) &&
            Objects.equals(second, that.second)) ||
            (Objects.equals(first, that.second) &&
                Objects.equals(second, that.first));
    }

    @Override
    public int hashCode() {
        return Objects.hash(first) + Objects.hash(second);
    }
}
