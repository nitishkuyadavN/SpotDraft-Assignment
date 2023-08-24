package com.spodraft.asana_airtable.entity;

import org.springframework.stereotype.Component;

@Component
public class Task {
    private String taskID;
    private String name;
    private String assignee;
    private String dueDate;
    private String description;
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", name=" + name + ", assignee=" + assignee + ", dueDate=" + dueDate
				+ ", description=" + description + "]";
	}
    
}
