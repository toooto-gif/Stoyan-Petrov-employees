package main.java.com.employeesApi.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import main.java.com.employeesApi.entities.Employee;
import main.java.com.employeesApi.entities.Project;
import main.java.com.employeesApi.models.EmployeePair;
import main.java.com.employeesApi.models.WorkedTime;

public class EmployeeServiceImpl implements EmployeeService {

    public Entry<EmployeePair, WorkedTime> getEmployeesFromFile(File file) {
        Map<Long, Project> projects = new HashMap<>();

        Map<EmployeePair, WorkedTime> employeePairs = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                List<String> data = Arrays.asList(line.split(", "));
                Long projectId = getProjectId(data);
                Project project = new Project(projectId);

                if (!projects.containsKey(projectId)) {
                    projects.put(projectId, project);
                }

                Employee employee = createEmployee(data);
                EmployeePair pair = new EmployeePair();
                pair.setFirst(employee);

                projects.get(projectId).getEmployees().stream().forEach(e -> {
                    pair.setSecond(e);

                    if (employeePairs.containsKey(pair)) {
                        WorkedTime value = employeePairs.get(pair);
                        Long timeTogether = calculateTime(pair);

                        value.addProject(project, timeTogether);
                        value.addTimeInMilliseconds(timeTogether);

                        employeePairs.put(pair, value);
                    } else {
                        Long time = calculateTime(pair);

                        if (time > 0) {
                            WorkedTime value = new WorkedTime();

                            value.addTimeInMilliseconds(time);
                            value.addProject(project, time);

                            employeePairs.put(pair, value);
                        }
                    }
                });
                projects.get(projectId).addEmployee(employee);
            }

            Map.Entry<EmployeePair, WorkedTime> bestPair = null;
            for (Map.Entry<EmployeePair, WorkedTime> entry : employeePairs.entrySet()) {
                if (bestPair == null) {
                    bestPair = entry;
                } else if (entry.getValue().getTimeInMilliseconds() > bestPair.getValue().getTimeInMilliseconds()) {
                    bestPair = entry;
                }
            }
            return bestPair;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Long calculateTime(EmployeePair pair) {
        Employee first = pair.getFirst();
        Employee second = pair.getSecond();

        Date started;
        Date ended;

        if (first.getStartDate().after(second.getStartDate())) {
            started = first.getStartDate();

        } else {
            started = second.getStartDate();
        }

        if (first.getEndDate().before(second.getEndDate())) {
            ended = first.getEndDate();
        } else {
            ended = second.getEndDate();
        }

        Long timeTogether = ended.getTime() - started.getTime();
        return timeTogether > 0 ? timeTogether : 0;
    }

    private static Long getProjectId(List<String> employeeData) {
        return Long.parseLong(employeeData.get(1));
    }

    private static Employee createEmployee(List<String> employeeData) {
        Long id = Long.parseLong(employeeData.get(0).trim());
        Date startDate = DateParserService.stringToDate(employeeData.get(2));
        Date endDate = DateParserService.stringToDate(employeeData.get(3).trim());

        return new Employee.EmployeeBuilder()
            .id(id)
            .startDate(startDate)
            .endDate(endDate)
            .build();
    }
}
