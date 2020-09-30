package com.mytasknowcobackend.kanban.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tasks implements Serializable{

    String taskName;
    String taskId;
    String taskType;
    String taskPriority;
    String taskDescription;
    String taskCreatedBy;
    String taskStartTime;
    String taskEndTime;
    String taskDuration;
    String taskStatus;

    public Tasks() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskCreatedBy() {
        return taskCreatedBy;
    }

    public void setTaskCreatedBy(String taskCreatedBy) {
        this.taskCreatedBy = taskCreatedBy;
    }

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(String taskDuration) {
        this.taskDuration = taskDuration;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Tasks(@JsonProperty("taskName") String taskName, @JsonProperty("taskId") String taskId, @JsonProperty("taskType") String taskType, @JsonProperty("taskPriority") String taskPriority, @JsonProperty("taskDescription") String taskDescription, @JsonProperty("taskCreatedBy") String taskCreatedBy, @JsonProperty("taskStartTime") String taskStartTime, @JsonProperty("taskEndTime") String taskEndTime, @JsonProperty("taskDuration") String taskDuration, @JsonProperty("taskStatus") String taskStatus) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.taskType = taskType;
        this.taskPriority = taskPriority;
        this.taskDescription = taskDescription;
        this.taskCreatedBy = taskCreatedBy;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
    }
}
