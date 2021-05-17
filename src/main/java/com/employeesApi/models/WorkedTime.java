package main.java.com.employeesApi.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import main.java.com.employeesApi.entities.Project;

public class WorkedTime {
    private Map<Project, Long> timeForProject;
    private Long timeInMilliseconds;

    public WorkedTime() {
        this.timeForProject = new HashMap<>();
        timeInMilliseconds = 0L;
    }

    public Map<Project, Long> getTimeForProject() {
        return Collections.unmodifiableMap(timeForProject);
    }

    public void setTimeForProject(Map<Project, Long> timeForProject) {
        this.timeForProject = timeForProject;
    }

    public Long getTimeInMilliseconds() {
        return this.timeInMilliseconds;
    }

    public void setTimeInMilliseconds(Long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public void addTimeInMilliseconds(Long timeInMilliseconds) {

        this.timeInMilliseconds += timeInMilliseconds;
    }

    public void addProject(Project project, Long timeTogether) {
        timeForProject.put(project, timeTogether);
    }
}
