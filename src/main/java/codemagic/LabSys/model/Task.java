package codemagic.LabSys.model;

public class Task {
    private Integer taskId;

    private String taskTitle;

    private Integer taskPubliser;

    private String taskDetails;
    
    private String taskDate;
 
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle == null ? null : taskTitle.trim();
    }

    public Integer getTaskPubliser() {
        return taskPubliser;
    }

    public void setTaskPubliser(Integer taskPubliser) {
        this.taskPubliser = taskPubliser;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails == null ? null : taskDetails.trim();
    }
    
    public String getTaskDate() {
 		return taskDate;
 	}

 	public void setTaskDate(String taskDate) {
 		this.taskDate = taskDate;
 	}
}