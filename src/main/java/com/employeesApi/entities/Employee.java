package main.java.com.employeesApi.entities;

import java.util.Date;
import java.util.Objects;

public class Employee {
    private Long id;
    private Date startDate;
    private Date endDate;

    public static class EmployeeBuilder {
        private Long id;
        private Date startDate;
        private Date endDate;

        public EmployeeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public EmployeeBuilder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
