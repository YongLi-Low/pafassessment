package ibf2022.paf.assessment.server.models;

import java.sql.Date;

// TODO: Task 4

public class Task {

    private String username;
    // private String taskId;
    private String description;
    private Integer priority;
    private Date dueDate;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    // public String getTaskId() {
    //     return taskId;
    // }
    // public void setTaskId(String taskId) {
    //     this.taskId = taskId;
    // }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public Task() {
    }
    public Task(String username, String description, Integer priority, Date dueDate) {
        this.username = username;
        // this.taskId = taskId;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "Task [username=" + username + ", description=" + description + ", priority=" + priority + ", dueDate="
                + dueDate + "]";
    }
    
    
}
